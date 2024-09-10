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

    /* fun updateMonthlyHostelPayment() {
        val dataRef = FirebaseDatabaseManager.monthlyHostelPayment.child(monthYear)
        val studentRef = dataRef.child(studentId.value)

        // Retrieve the existing student data
        studentRef.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val existingStudent = documentSnapshot.toObject(MonthlyPayment::class.java)

                // Create a new MonthlyPayment object with the updated field
                val updatedStudent = existingStudent?.copy(
                    // Add the new field here (e.g., "paymentStatus")
                    paymentStatus = "Pending" // Replace with your desired value
                )

                // Update the document with the updated data
                studentRef.set(updatedStudent!!)
                    .addOnSuccessListener {
                        Log.d("StudentUpdate", "Student updated successfully")
                    }
                    .addOnFailureListener { exception ->
                        Log.e("StudentUpdateError", "Error updating student: $exception")
                    }
            } else {
                Log.e("StudentUpdateError", "Student not found")
            }
        }
    } */
}