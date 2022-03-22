package com.bws.izharassignment.ui.test

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bws.izharassignment.R
import com.bws.izharassignment.constants.Common
import com.bws.izharassignment.response.TestedData
import com.bws.izharassignment.ui.LoadSourceActivity
import com.bws.izharassignment.utils.NetworkUtils
import com.bws.izharassignment.utils.ConnectionAlertDialog
import kotlinx.android.synthetic.main.dialog_test_details.*

class TestAdapter(private val items: ArrayList<TestedData>) :
    RecyclerView.Adapter<TestAdapter.ViewHolder>() {
    private var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_test, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItems = items[position]

        holder.txtTestedOfDate.text = "Tested as of : " + dataItems.testedasof
        holder.txtDailyRTPCR.text =
            "Daily RTPCR sample collected : " + dataItems.dailyrtpcrsamplescollectedicmrapplication
        holder.txtSamReporToday.text = "Samples reported today : " + dataItems.samplereportedtoday
        holder.txtTotalDoese.text = "Total doese adminstreted : " + dataItems.totaldosesadministered
        holder.txtSource.text = "Click to View"

        val spannableString = SpannableString("Click to View")
        spannableString.setSpan(UnderlineSpan(), 0, spannableString.length, 0)
        holder.txtSource.text = spannableString
        holder.txtSource.setTextColor(Color.BLUE)

        holder.txtSource.setOnClickListener {

            if (NetworkUtils.isNetworkAvailable(context!!)) {
                Common.sourceURL = items[position].source
                context?.startActivity(Intent(context, LoadSourceActivity::class.java))
            } else {
                ConnectionAlertDialog().dialog(context!!)
            }
        }

        holder.itemView.setOnClickListener {
            dialogTest(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val txtTestedOfDate: TextView = itemView.findViewById(R.id.txtTestedOfDate)
        val txtDailyRTPCR: TextView = itemView.findViewById(R.id.txtDailyRTPCR)
        val txtSamReporToday: TextView = itemView.findViewById(R.id.txtSamReporToday)
        val txtTotalDoese: TextView = itemView.findViewById(R.id.txtTotalDoese)
        val txtSource: TextView = itemView.findViewById(R.id.txtSource)
    }


    private fun dialogTest(itemPos: Int) {
        val dialog = Dialog(context!!, R.style.NewDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_test_details)
        dialog.getWindow()
            ?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val item = items[itemPos]

        dialog.txtOver45YerStDode.text = "Over 45 Year 1st does : " + item.over45years1stdose
        dialog.txtOver45YersecondDode.text = "Over 45 Year 2nd does : " + item.over45years2nddose
        dialog.txtOver60YerFirstDode.text = "Over 60 Year 1st does : " + item.over60years1stdose
        dialog.txtOver60YerSecondDode.text = "Over 60 Year 2nd does : " + item.over60years2nddose
        dialog.txtTotalSampleTested.text = "Total sample tested : " + item.totalsamplestested
        dialog.txtTotalvaccineConsume.text =
            "Total vaccine consume : " + item.totalvaccineconsumptionincludingwastage
        dialog.txtUpdateTime.text = "Update time : " + item.updatetimestamp

        dialog.txt_Ok.setOnClickListener() {
            dialog.dismiss()
        }
        dialog.show()
    }
}