package com.alsalam.alsalamadminapp.ViewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alsalam.alsalamadminapp.Firebase.FirebaseDatabaseManager
import com.alsalam.alsalamadminapp.Model.DailyExpense
import com.alsalam.alsalamadminapp.Model.Expenditure
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class PreviousPayments: ViewModel() {

    val dateByAdmin: MutableStateFlow<String> = MutableStateFlow<String>("")
    val selectedDate: MutableStateFlow<String> = MutableStateFlow<String>("")
    val paymentListCollection: MutableStateFlow<List<DailyExpense>> = MutableStateFlow<List<DailyExpense>>(emptyList())
    val paymentListExpense: MutableStateFlow<List<Expenditure>> = MutableStateFlow<List<Expenditure>>(emptyList())

    val totalCollection: MutableStateFlow<Double> = MutableStateFlow<Double>(0.0)

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

    fun loadCollection() {
        viewModelScope.launch {
            convertDateFormat()
            val classRef = FirebaseDatabaseManager.dailyCollection.child("Date_${selectedDate.value}")
            classRef.get().addOnSuccessListener { dataSnapshot ->
                val studentsList = mutableListOf<DailyExpense>()
                totalCollection.value = dataSnapshot.children.sumOf { child ->
                    child.getValue(DailyExpense::class.java)?.amount ?: 0.0
                }
                dataSnapshot.children.forEach { child ->
                    val student = child.getValue(DailyExpense::class.java)
                    student?.let { studentsList.add(it) }
                }
                paymentListCollection.value = studentsList
                dateByAdmin.value = ""
                selectedDate.value = ""
            }.addOnFailureListener { exception ->
                Log.e("LoadStudentsError", "Failed to load students: ${exception.message}")
            }
        }
    }


    fun loadExpenditure() {
        viewModelScope.launch {
            convertDateFormat()
            val classRef = FirebaseDatabaseManager.dailyExpenditure.child("Date_${selectedDate.value}")
            classRef.get().addOnSuccessListener { dataSnapshot ->
                val studentsList = mutableListOf<Expenditure>()
                dataSnapshot.children.forEach { child ->
                    val student = child.getValue(Expenditure::class.java)
                    student?.let { studentsList.add(it) }
                }
                paymentListExpense.value = studentsList
            }.addOnFailureListener { exception ->
                Log.e("LoadStudentsError", "Failed to load students: ${exception.message}")
            }
        }
    }

}



