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


    @SuppressLint("SimpleDateFormat")
    fun convertDateFormat() {
        for(i in dateByAdmin.value){
            if(i=='/'){
                selectedDate.value += "_"
            }
            else{
                selectedDate.value += i
            }
        }
    }

    fun loadStudents() {
        viewModelScope.launch {
            convertDateFormat()
            val classRef = FirebaseDatabaseManager.dailyCollection.child("Date_${selectedDate.value}")
            classRef.get().addOnSuccessListener { dataSnapshot ->
                val studentsList = mutableListOf<DailyExpense>()
                dataSnapshot.children.forEach { child ->
                    val student = child.getValue(DailyExpense::class.java)
                    student?.let { studentsList.add(it) }
                }
                paymentList.value = studentsList
                dateByAdmin.value = ""
                selectedDate.value = ""
            }.addOnFailureListener { exception ->
                Log.e("LoadStudentsError", "Failed to load students: ${exception.message}")
            }
        }
    }




}