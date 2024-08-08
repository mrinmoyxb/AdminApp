package com.alsalam.alsalamadminapp.ViewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alsalam.alsalamadminapp.Firebase.FirebaseDatabaseManager
import com.alsalam.alsalamadminapp.Model.DailyExpense
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class PreviousPayments: ViewModel() {

    val dateByAdmin: MutableStateFlow<String> = MutableStateFlow<String>("")
    val selectedDate: MutableStateFlow<String> = MutableStateFlow<String>("")
    val paymentList: MutableStateFlow<List<DailyExpense>> = MutableStateFlow<List<DailyExpense>>(emptyList())


    fun loadStudents() {
        convertDateFormat()
        viewModelScope.launch {
            val classRef = FirebaseDatabaseManager.dailyCollection.child("Date_${selectedDate}")
            classRef.get().addOnSuccessListener { dataSnapshot ->
                val studentsList = mutableListOf<DailyExpense>()
                dataSnapshot.children.forEach { child ->
                    val student = child.getValue(DailyExpense::class.java)
                    student?.let { studentsList.add(it) }
                }
                paymentList.value = studentsList
            }.addOnFailureListener { exception ->
                Log.e("LoadStudentsError", "Failed to load students: ${exception.message}")
            }
        }
    }


    @SuppressLint("SimpleDateFormat")
    fun convertDateFormat() {
        val inputFormat = SimpleDateFormat("dd/MM/yyyy")
        val outputFormat = SimpleDateFormat("dd_MM_yyyy")

        val date = inputFormat.parse(dateByAdmin.value)
        if (date != null) {
            selectedDate.value = date.toString()
        }
    }

}