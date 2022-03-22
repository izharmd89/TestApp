package com.bws.izharassignment.ui.state

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bws.izharassignment.R
import com.bws.izharassignment.response.StateWise
import com.bws.izharassignment.utils.ConnectivityReceiver
import kotlinx.android.synthetic.main.dialog_cases_details.txtOk
import kotlinx.android.synthetic.main.dialog_state_details.*

class StateWiseAdapter(private val items: ArrayList<StateWise>) :
    RecyclerView.Adapter<StateWiseAdapter.ViewHolder>(){
    private var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_state_wise, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentOrder = items[position]

        holder.txtState.text = "State : " + currentOrder.state
        holder.txtActive.text = "Active : " + currentOrder.active
        holder.txtRecovered.text = "Recovered : " + currentOrder.recovered
        holder.txtDeath.text = "Deaths : " + currentOrder.deaths

        holder.itemView.setOnClickListener {
            dialogState(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val txtState: TextView = itemView.findViewById(R.id.txtState)
        val txtActive: TextView = itemView.findViewById(R.id.txtActive)
        val txtRecovered: TextView = itemView.findViewById(R.id.txtRecovered)
        val txtDeath: TextView = itemView.findViewById(R.id.txtDeath)
    }

    private fun dialogState(itemPos: Int) {
        val dialog = Dialog(context!!, R.style.NewDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_state_details)
        dialog.getWindow()
            ?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val item = items[itemPos]

        dialog.txtConfirmed.text = "Confirmed : " + item.confirmed
        dialog.txtDeltaconfirmed.text = "Daily Total confirm : " + item.deltaconfirmed
        dialog.txtDeltadeaths.text = "Daily Deaths : " + item.deltadeaths
        dialog.txtDailyRecovered.text = "Recovered : " + item.recovered
        dialog.txtLastUpdateDateTime.text = "Last Update Date Time : " + item.lastupdatedtime
        dialog.txtMigratedother.text = "Migrated other : " + item.migratedother
        dialog.txtStatecode.text = "State code : " + item.statecode
        dialog.txtStatenotes.text = "State notes : " + item.statenotes

        dialog.txtOk.setOnClickListener() {
            dialog.dismiss()
        }
        dialog.show()
    }
}