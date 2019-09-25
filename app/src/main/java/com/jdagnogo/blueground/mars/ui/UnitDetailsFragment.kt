package com.jdagnogo.blueground.mars.ui

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar
import com.jdagnogo.blueground.mars.R
import com.jdagnogo.blueground.mars.adapter.MarsUnitsViewHolder
import com.jdagnogo.blueground.mars.api.LoginDao
import com.jdagnogo.blueground.mars.api.model.BookParameters
import com.jdagnogo.blueground.mars.api.model.UnitBooked
import com.jdagnogo.blueground.mars.model.MarsUnitDetails
import com.jdagnogo.blueground.mars.modelView.UnitDetailsViewModel
import com.jdagnogo.blueground.mars.utils.Result
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_alert_dialog.*
import kotlinx.android.synthetic.main.fragment_unit_details.view.*
import javax.inject.Inject

class UnitDetailsFragment : Fragment() {
    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: UnitDetailsViewModel
    private lateinit var marsUnit: MarsUnitDetails
    private val args: UnitDetailsFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_unit_details, container, false)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(UnitDetailsViewModel::class.java)
        view.book.setOnClickListener{
            if (!::marsUnit.isInitialized) return@setOnClickListener
            viewModel.bookUnit(marsUnit.id,marsUnit.availability[0])
        }
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(UnitDetailsViewModel::class.java)
        viewModel.unitDetails.observe(this, unitDetailsObserver)
        viewModel.bookUnit.observe(this,bookUnitObserver)
        viewModel.fetchMArsUnitDetailsInformation(args.id)
    }
    val bookUnitObserver = Observer<Result<UnitBooked>> { result ->
        when (result.status) {
            Result.Status.SUCCESS -> {
                createDialog(result.data)
            }
            Result.Status.LOADING -> return@Observer
            Result.Status.ERROR -> view?.unit_detail_layout?.let {
                Snackbar.make(
                    it,
                    result.message ?: "",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
    fun createDialog(unitBooked: UnitBooked?) {
        if (unitBooked == null) return
        val dialog = Dialog(requireContext())
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(R.layout.custom_alert_dialog)
            reference_value.text = unitBooked.reference
            years_value.text = unitBooked.year.toString()
            close.setOnClickListener{
                dismiss()
                findNavController().popBackStack()
            }
            show()
        }

    }

    val unitDetailsObserver = Observer<Result<MarsUnitDetails>> { result ->
        when (result.status) {
            Result.Status.SUCCESS -> {
                marsUnit = result.data ?: MarsUnitDetails()
                updateUnitInformation()
            }
            Result.Status.LOADING -> return@Observer
            Result.Status.ERROR -> view?.unit_detail_layout?.let {
                Snackbar.make(
                    it,
                    result.message ?: "",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    fun updateUnitInformation() {
        marsUnit.apply {
            view?.apply {
                title?.text = marsUnit.title
                region?.text = marsUnit.region
                ratingBar?.numStars = rating.toInt()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    description?.text =  Html.fromHtml(marsUnit.description, Html.FROM_HTML_MODE_LEGACY).toString()
                } else {
                    Html.fromHtml(marsUnit.description).toString()
                }
                amenities?.text = displayAmentitiesText()
                price.text = MarsUnitsViewHolder.CURRENCY + marsUnit.price
            }

            if (!pictures[0].isEmpty()) {
                view?.imageView2?.let {
                    Glide.with(this@UnitDetailsFragment)
                        .load(LoginDao.ENDPOINT + pictures[0])
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(it)
                }
            }
        }
    }

    fun displayAmentitiesText(): String {
        val result = StringBuilder()
        marsUnit.amenities.map {
            result.append("- ").append(it).append("\n")
        }
        return result.toString()
    }
}