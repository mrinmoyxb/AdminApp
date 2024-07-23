package com.alsalam.alsalamadminapp.ViewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alsalam.alsalamadminapp.Firebase.FirebaseDatabaseManager
import com.alsalam.alsalamadminapp.Model.PaymentTypes
import com.alsalam.alsalamadminapp.Model.StudentFee
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class PaymentViewModel: ViewModel() {

    var currentActiveRollNo: MutableStateFlow<String> = MutableStateFlow<String>("")
    var currentActiveName: MutableStateFlow<String> = MutableStateFlow<String>("")
    var currentActiveDob: MutableStateFlow<String> = MutableStateFlow<String>("")
    var currentPaymentAmount: MutableStateFlow<String> = MutableStateFlow<String>("")
    var currentIsFeePaid: MutableStateFlow<Boolean> = MutableStateFlow<Boolean>(false)
    var serviceSelected: MutableStateFlow<Int> = MutableStateFlow<Int>(-1)
    var gradeSelected: MutableStateFlow<Int> = MutableStateFlow<Int>(0)

    // fetched data
    val studentsPaymentFetched = MutableStateFlow<List<StudentFee>>(emptyList())

    private val currentDate = Date()

    @SuppressLint("SimpleDateFormat")
    val formatter = SimpleDateFormat("dd_MM_yyyy")
    private val newDate: String = formatter.format(Date(currentDate.time))

    // ADD HOSTEL FEES
    fun addHostelFees() {
        viewModelScope.launch {
            val hostelFeeOfStudent: StudentFee = StudentFee(currentActiveName.value, currentActiveRollNo.value, PaymentTypes.HostelFees,
                currentPaymentAmount.value.toDouble(), currentDate.time, currentIsFeePaid.value)

            FirebaseDatabaseManager.firestoreRef
                .collection("Payments")
                .document("Grade_${gradeSelected.value}")
                .collection("RollNo_${currentActiveRollNo.value}")
                .add(hostelFeeOfStudent)
                .addOnSuccessListener {
                    currentPaymentAmount.value = ""
                    Log.d("Firebase FireStore Success", "record updated successfully!")
                }
                .addOnFailureListener {
                    Log.d("Firebase FireStore Failed", "record updated successfully!")
                }
        }
    }

    // ADD TUITION FEES
    fun addTuitionFees() {
        viewModelScope.launch {
            val tuitionFeeOfStudent: StudentFee = StudentFee(currentActiveName.value, currentActiveRollNo.value, PaymentTypes.TuitionFees,
                currentPaymentAmount.value.toDouble(), currentDate.time, currentIsFeePaid.value
            )

            FirebaseDatabaseManager.firestoreRef
                .collection("Payments")
                .document("Grade_${gradeSelected.value}")
                .collection("RollNo_${currentActiveRollNo.value}")
                .add(tuitionFeeOfStudent)
                .addOnSuccessListener {
                    currentPaymentAmount.value = ""
                    Log.d("Firebase FireStore Success", "record updated successfully!")
                }
                .addOnFailureListener {
                    Log.d("Firebase FireStore Failed", "record updated successfully!")
                }
        }
    }

    // SELECT GRADE
    fun selectGrade(grade: Int) {
        gradeSelected.value = grade
    }

    // FETCH DATA
    fun fetchStudentFees() {
        viewModelScope.launch {
            FirebaseDatabaseManager.firestoreRef
                .collection("Payments")
                .document("Grade_${gradeSelected.value}")
                .collection("RollNo_${currentActiveRollNo.value}")
                .get()
                .addOnSuccessListener { result ->
                    val fetchedData = result.toObjects(StudentFee::class.java)
                    studentsPaymentFetched.value = fetchedData
                }
                .addOnFailureListener { e ->
                    Log.w("FireStore ERROR", "Error fetching documents", e)
                }
        }
    }
}




