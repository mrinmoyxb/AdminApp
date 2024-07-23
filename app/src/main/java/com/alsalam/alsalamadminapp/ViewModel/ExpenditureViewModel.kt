package com.alsalam.alsalamadminapp.ViewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alsalam.alsalamadminapp.Firebase.FirebaseDatabaseManager
import com.alsalam.alsalamadminapp.Model.Expenditure
import com.alsalam.alsalamadminapp.Model.randomStringGenerator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class ExpenditureViewModel: ViewModel() {

    val category: MutableStateFlow<String> = MutableStateFlow<String>("")
    var amount: MutableStateFlow<String> = MutableStateFlow<String>("")

    val expenseList: MutableStateFlow<List<Expenditure>> = MutableStateFlow<List<Expenditure>>(emptyList())
    val totalExpenditure: MutableStateFlow<Double> = MutableStateFlow<Double>(0.0)

    val currentDate = Date()
    @SuppressLint("SimpleDateFormat", "WeekBasedYear")
    val formatter = SimpleDateFormat("dd_MM_YYYY")
    val newDate = formatter.format(Date(currentDate.time))

    fun addExpense() {
        val expense: Expenditure = Expenditure(category.value, amount.value.toDouble(), currentDate.time)
        val expenseRef = FirebaseDatabaseManager.dailyExpenditure.child("Date_$newDate")
        val randomID = randomStringGenerator()
        val studentRef = expenseRef.child("${category.value}_${randomID}")
        studentRef.setValue(expense)
            .addOnSuccessListener{
                category.value = ""
                amount.value = ""
                Log.d("Firebase FireStore Success", "record updated successfully!")
            }
            .addOnFailureListener {
                Log.d("Firebase FireStore Failed", "record updated successfully!")
            }
    }

    fun loadDailyExpense() {
        viewModelScope.launch {
            val expenseRef = FirebaseDatabaseManager.dailyExpenditure.child("Date_$newDate")
            expenseRef.get().addOnSuccessListener { dataSnapshot ->
                val studentsList = mutableListOf<Expenditure>()
                dataSnapshot.children.forEach { child ->
                    val student = child.getValue(Expenditure::class.java)
                    student?.let { studentsList.add(it) }
                }
                expenseList.value = studentsList
            }.addOnFailureListener { exception ->
                Log.e("LoadStudentsError", "Failed to load students: ${exception.message}")
            }
        }
    }

    fun loadTotalDailyExpense() {
        viewModelScope.launch {
            val expenseRef = FirebaseDatabaseManager.dailyExpenditure.child("Date_$newDate")
            expenseRef.get().addOnSuccessListener { dataSnapshot ->
                val amountList = mutableListOf<Double>()
                dataSnapshot.children.forEach { child ->
                    val student = child.getValue(Expenditure::class.java)
                    student?.let { amountList.add(it.amount) }
                }
                val totalExpense = amountList.fold(0.0){acc, amount -> acc + amount}
                totalExpenditure.value = totalExpense

            }.addOnFailureListener { exception ->
                Log.e("LoadStudentsError", "Failed to load students: ${exception.message}")
            }
        }
    }


    private fun reset(){
        category.value = ""
        amount.value = ""
    }

}