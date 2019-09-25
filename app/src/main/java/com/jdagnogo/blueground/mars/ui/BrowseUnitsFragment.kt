package com.jdagnogo.blueground.mars.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jdagnogo.blueground.mars.R
import com.jdagnogo.blueground.mars.adapter.MarsUnitsAdapter
import com.jdagnogo.blueground.mars.modelView.BrowseUnitsViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_browse_units.view.*
import javax.inject.Inject

class BrowseUnitsFragment : Fragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: BrowseUnitsViewModel
    private lateinit var marsUnitsAdapter: MarsUnitsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_browse_units, container, false)
        val recyclerView = view.unit_list
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(BrowseUnitsViewModel::class.java)
        marsUnitsAdapter = MarsUnitsAdapter()
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerView.adapter = marsUnitsAdapter
        viewModel.unitList.observe(this, Observer {
            marsUnitsAdapter.submitList(it)
        })
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }
}