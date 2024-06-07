package com.example.hobbyapp.global

import android.content.Context
import androidx.appcompat.app.AlertDialog

class Global{
    companion object {
        val baseUrl = "http://182.1.88.69/hobby-webservice/"
        fun makeAlert(context: Context, title:String, message:String){
            val alert = AlertDialog.Builder(context)
            alert.setTitle(title)
            alert.setMessage(message)
            alert.setPositiveButton("OK") { _,_ ->}
            alert.show()
        }
    }
}
