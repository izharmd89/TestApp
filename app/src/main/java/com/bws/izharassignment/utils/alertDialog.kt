package com.bws.izharassignment.utils

import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class alertDialog {

    fun dialog(context: Context) {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setMessage("Source can not be view as app is running offline")
            // if the dialog is cancelable
            .setCancelable(false)

            // negative button text and action
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("AlertDialog")
        // show alert dialog
        alert.show()
    }
}