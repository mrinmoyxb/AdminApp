package com.alsalam.alsalamadminapp.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alsalam.alsalamadminapp.Firebase.FirebaseDatabaseManager
import com.alsalam.alsalamadminapp.Model.MonthlyPayment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AdminApprovePaymentViewModel: ViewModel() {

    var monthSelected: MutableStateFlow<String> = MutableStateFlow<String>("")
    var studentIdSelected: MutableStateFlow<String> = MutableStateFlow<String>("")

    var students: MutableLiveData<List<MonthlyPayment>> = MutableLiveData<List<MonthlyPayment>>()


    // Fetch students from database based on monthly payment
    fun loadMonthlyHostelPayment() {
        viewModelScope.launch {
            val classRef = FirebaseDatabaseManager.monthlyHostelPayment.child(monthSelected.value)
            classRef.get().addOnSuccessListener { dataSnapshot ->
                val studentsList = mutableListOf<MonthlyPayment>()
                dataSnapshot.children.forEach { child ->
                    val student = child.getValue(MonthlyPayment::class.java)
                    student?.let { studentsList.add(it) }
                }
                students.value = studentsList
            }.addOnFailureListener { exception ->
                Log.e("LoadStudentsError", "Failed to load students: ${exception.message}")
            }
        }
    }

    fun loadAdmissionPayment() {
        viewModelScope.launch {
            val classRef = FirebaseDatabaseManager.monthlyAdmissionPayment.child(monthSelected.value)
            classRef.get().addOnSuccessListener { dataSnapshot ->
                val studentsList = mutableListOf<MonthlyPayment>()
                dataSnapshot.children.forEach { child ->
                    val student = child.getValue(MonthlyPayment::class.java)
                    student?.let { studentsList.add(it) }
                }
                students.value = studentsList
            }.addOnFailureListener { exception ->
                Log.e("LoadStudentsError", "Failed to load students: ${exception.message}")
            }
        }
    }

    fun loadTuitionPayment() {
        viewModelScope.launch {
            val classRef = FirebaseDatabaseManager.monthlyTuitionPayment.child(monthSelected.value)
            classRef.get().addOnSuccessListener { dataSnapshot ->
                val studentsList = mutableListOf<MonthlyPayment>()
                dataSnapshot.children.forEach { child ->
                    val student = child.getValue(MonthlyPayment::class.java)
                    student?.let { studentsList.add(it) }
                }
                students.value = studentsList
            }.addOnFailureListener { exception ->
                Log.e("LoadStudentsError", "Failed to load students: ${exception.message}")
            }
        }
    }


    fun updateMonthlyHostelPayment() {
        val dataRef = FirebaseDatabaseManager.monthlyHostelPayment.child(monthSelected.value)
        val studentRef = dataRef.child(studentIdSelected.value)

        val updates = mapOf<String, Boolean>(
            "approveByAdmin" to true
        )

        studentRef.updateChildren(updates)
            .addOnSuccessListener {
                Log.d("StudentUpdate", "Student document updated successfully")
            }
            .addOnFailureListener { exception ->
                Log.e("StudentUpdateError", "Error updating student document: $exception")
            }
    }

}