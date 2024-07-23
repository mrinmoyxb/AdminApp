package com.alsalam.alsalamadminapp.Model

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat", "WeekBasedYear")
fun main(){
    val currentDate = Date()
    val formatter = SimpleDateFormat("dd_MM_YYYY")
    val newDate = formatter.format(Date(currentDate.time))
    print("DATE: $newDate")
}