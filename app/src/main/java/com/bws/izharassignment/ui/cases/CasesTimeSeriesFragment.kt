package com.bws.izharassignment.ui.cases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bws.izharassignment.R
import com.bws.izharassignment.constants.Common.arrCases
import com.bws.izharassignment.constants.Common.pullToRefresh
import com.bws.izharassignment.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_covid.*
import kotlinx.android.synthetic.main.fragment_covid.view.*


class CasesTimeSeriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_covid, container, false)
        view.rvCovidData.layoutManager = LinearLayoutManager(context)

        if (arrCases.isNotEmpty()) {
            view.txtNoDataFound.visibility = View.GONE
            val adapter = CasesAdapter(arrCases)
            view.rvCovidData.adapter = adapter
            adapter.notifyDataSetChanged()
        } else {
            view.txtNoDataFound.text = "Data not found"
            view.txtNoDataFound.visibility = View.VISIBLE
        }

        view.itemsswipetorefresh.setOnRefreshListener {
            pullToRefresh = true
            (activity as MainActivity)?.callAPI()
            itemsswipetorefresh.isRefreshing = false
        }

        return view
    }
}

