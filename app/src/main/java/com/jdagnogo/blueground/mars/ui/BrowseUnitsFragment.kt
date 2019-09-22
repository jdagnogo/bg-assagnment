package com.jdagnogo.blueground.mars.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jdagnogo.blueground.mars.R
import com.jdagnogo.blueground.mars.adapter.UnitsAdapter
import com.jdagnogo.blueground.mars.model.MarsUnit
import com.jdagnogo.blueground.mars.modelView.BrowseUnitsViewModel
import kotlinx.android.synthetic.main.fragment_browse_units.view.*
import javax.inject.Inject

class BrowseUnitsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: BrowseUnitsViewModel
    private val adapter = UnitsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_browse_units, container, false)
        val recyclerView = view.unit_list
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.adapter = adapter
        return view
    }

    @UiThread
    fun updateList(untis: List<MarsUnit>) {
        adapter.mDataset.clear()
        adapter.mDataset.addAll(untis)
        adapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateList(fakeValue())
    }

    fun fakeValue(): List<MarsUnit> {
        val unit1 = MarsUnit("https://www.vanupied.com/wp-content/uploads/68550354.jpg", "toto", 0, "njegjznkzengkjzngkezegnzgnznegjngzjnzjkgnjejgnjezgjzejzjjjgjgjnejknzjgjzejgjzkgzjkgjejekjkngjjgnznzeg", false,4, 1000)
        val unit2 = MarsUnit("", "titi", 1, "poor thing",true, 1, 200)
        return listOf(unit1, unit2)
    }
}