package com.alsalam.alsalamadminapp.ViewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alsalam.alsalamadminapp.Firebase.FirebaseDatabaseManager
import com.alsalam.alsalamadminapp.Model.DailyExpense
import com.alsalam.alsalamadminapp.Model.PaymentTypes
import com.alsalam.alsalamadminapp.Model.randomStringGenerator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class DailyCollectionViewModel: ViewModel() {
    val studentName: MutableStateFlow<String> = MutableStateFlow<String>("")
    val paymentTypes: MutableStateFlow<PaymentTypes> = MutableStateFlow<PaymentTypes>(PaymentTypes.DefaultFees)
    val amount: MutableStateFlow<Double> = MutableStateFlow<Double>(0.0)
    val grade: MutableStateFlow<String> = MutableStateFlow<String>("")

    val paymentList: MutableStateFlow<List<DailyExpense>> = MutableStateFlow<List<DailyExpense>>(emptyList())
    val totalCollection: MutableStateFlow<Double> = MutableStateFlow<Double>(0.0)

    private val currentDate = Date()
    @SuppressLint("SimpleDateFormat", "WeekBasedYear")
    val formatter = SimpleDateFormat("dd_MM_YYYY")
    val newDate = formatter.format(Date(currentDate.time))

    fun storePayment(){
        val payment: DailyExpense = DailyExpense(studentName.value, paymentTypes.value, amount.value, grade.value, currentDate.time)
        val payRef = FirebaseDatabaseManager.dailyCollection.child("Date_$newDate")
        val randomID = randomStringGenerator()
        val studentRef = payRef.child("${studentName.value}_${grade.value}_${randomID}")
        studentRef.setValue(payment)
            .addOnSuccessListener{
                amount.value = 0.0
                Log.d("Firebase FireStore Success", "record updated successfully!")
            }
            .addOnFailureListener {
                Log.d("Firebase FireStore Failed", "record updated successfully!")
            }
    }

    fun loadDailyCollection() {
        viewModelScope.launch {
            val payRef = FirebaseDatabaseManager.dailyCollection.child("Date_$newDate")
            payRef.get().addOnSuccessListener { dataSnapshot ->
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

    fun loadTotalDailyCollection() {
        viewModelScope.launch {
            val payRef = FirebaseDatabaseManager.dailyCollection.child("Date_$newDate")
            payRef.get().addOnSuccessListener { dataSnapshot ->
                val amountList = mutableListOf<Double>()
                dataSnapshot.children.forEach { child ->
                    val student = child.getValue(DailyExpense::class.java)
                    student?.let { amountList.add(it.amount) }
                }
                val totalAmount = amountList.fold(0.0) { acc, amount -> acc + amount }
                totalCollection.value = totalAmount
            }.addOnFailureListener { exception ->
                Log.e("LoadStudentsError", "Failed to load students: ${exception.message}")
            }
        }
    }

}

