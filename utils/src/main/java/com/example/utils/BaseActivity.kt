package com.example.utils

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract fun setToolbarTitle(title: String)

    fun showAlertDialog(clickListener: () -> Unit = {}) {
        val alertDialog: AlertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle("Error")
        alertDialog.setMessage("Something went wrong")
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK") { dialog, which ->
            clickListener.invoke()
            dialog.dismiss()
        }
        alertDialog.show()
    }
}