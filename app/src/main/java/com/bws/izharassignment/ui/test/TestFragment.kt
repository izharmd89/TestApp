package com.bws.izharassignment.ui.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bws.izharassignment.R
import com.bws.izharassignment.constants.Common
import com.bws.izharassignment.constants.Common.arrTestData
import com.bws.izharassignment.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_covid.*
import kotlinx.android.synthetic.main.fragment_covid.view.*

class TestFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_covid, container, false)

        view.rvCovidData.layoutManager = LinearLayoutManager(context)

        if (arrTestData.isNotEmpty()) {
            view.txtNoDataFound.visibility = View.GONE
            val adapter = TestAdapter(arrTestData)
            view.rvCovidData.adapter = adapter
            adapter.notifyDataSetChanged()
        } else {
            view.txtNoDataFound.text = "Data not found"
            view.txtNoDataFound.visibility = View.VISIBLE
        }

        //PULL TO REFRESH
        view.itemsswipetorefresh.setOnRefreshListener {
            Common.pullToRefresh = true
            (activity as MainActivity)?.callAPI()
            itemsswipetorefresh.isRefreshing = false
        }
        return view
    }
}