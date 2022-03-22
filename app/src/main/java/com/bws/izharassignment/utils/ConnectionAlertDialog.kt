package com.bws.izharassignment.utils

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

class ConnectionAlertDialog {

    fun dialog(context: Context) {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setMessage("Source can not be view as app is running offline")
            .setCancelable(false)
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })

        val alert = dialogBuilder.create()
        alert.setTitle("AlertDialog")
        alert.show()
    }
}