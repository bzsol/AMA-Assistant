package hu.unideb.assistant.utils

import android.annotation.SuppressLint
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*


object Time {

    @SuppressLint("SimpleDateFormat")
    fun TimeStamp(): String{
        val time = Timestamp(System.currentTimeMillis())
        val dateFormat = SimpleDateFormat.getDateTimeInstance()
        return dateFormat.format(Date(time.time)).toString()
    }
}