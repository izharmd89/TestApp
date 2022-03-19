package com.bws.izharassignment.ui.cases

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bws.izharassignment.R
import com.bws.izharassignment.response.CaseTimeSerese
import kotlinx.android.synthetic.main.dialog_cases_details.*


class CasesAdapter(private val items: ArrayList<CaseTimeSerese>) :
    RecyclerView.Adapter<CasesAdapter.ViewHolder>() {
    private var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_cases, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItems = items[position]

        holder.txtDate.text = "Date : " + dataItems.date
        holder.txTotalConfirm.text = "Total Confirm : " + dataItems.totalconfirmed
        holder.txtTotalDecrease.text = "Total Decrease : " + dataItems.totaldeceased
        holder.txtTotalRecovered.text = "Total Recovered : " + dataItems.totalrecovered

        holder.itemView.setOnClickListener {
            dialogCases(position)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val txtDate: TextView = itemView.findViewById(R.id.txtDate)
        val txTotalConfirm: TextView = itemView.findViewById(R.id.txTotalConfirm)
        val txtTotalDecrease: TextView = itemView.findViewById(R.id.txtTotalDecrease)
        val txtTotalRecovered: TextView = itemView.findViewById(R.id.txtTotalRecovered)
    }


    private fun dialogCases(itemPos: Int) {
        val dialog = Dialog(context!!, R.style.NewDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_cases_details)
        dialog.getWindow()
            ?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val item = items[itemPos]

        dialog.txtDailyconfirmed.text = "Daily Confirmed : " + item.totalconfirmed
        dialog.txtDailydeceased.text = "Daily Deceased : " + item.dailydeceased
        dialog.txtDailyrecovered.text = "Daily Recovered : " + item.dailyrecovered
        dialog.txtDateymd.text = "Date ymd : " + item.dateymd

        dialog.txtOk.setOnClickListener() {
            dialog.dismiss()
        }
        dialog.show()
    }
}