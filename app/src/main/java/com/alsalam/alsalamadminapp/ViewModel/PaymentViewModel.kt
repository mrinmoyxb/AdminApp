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
    var gradeSelected: MutableStateFlow<String> = MutableStateFlow<String>("")
    var currentStudentId: MutableStateFlow<String> = MutableStateFlow<String>("")

    // Extra
    var fatherName: MutableStateFlow<String> = MutableStateFlow<String>("")
    var motherName: MutableStateFlow<String> = MutableStateFlow<String>("")
    var village: MutableStateFlow<String> = MutableStateFlow<String>("")
    var postOffice: MutableStateFlow<String> = MutableStateFlow<String>("")
    var policeStation: MutableStateFlow<String> = MutableStateFlow<String>("")
    var district: MutableStateFlow<String> = MutableStateFlow<String>("")
    var pin: MutableStateFlow<String> = MutableStateFlow<String>("")
    var mobileNo: MutableStateFlow<String> = MutableStateFlow<String>("")
    var admissionDate: MutableStateFlow<String> = MutableStateFlow<String>("")
    var admissionFees: MutableStateFlow<String> = MutableStateFlow<String>("")
    var monthlyFees: MutableStateFlow<String> = MutableStateFlow<String>("")
    var imageUrl: MutableStateFlow<String?> = MutableStateFlow<String?>(null)

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
                .document("${gradeSelected.value}")
                .collection("${currentStudentId.value}")
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
                .document(gradeSelected.value)
                .collection(currentStudentId.value)
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

    // ADD OTHER FEES
    fun addOtherFees() {
        viewModelScope.launch {
            val tuitionFeeOfStudent: StudentFee = StudentFee(currentActiveName.value, currentActiveRollNo.value, PaymentTypes.OtherFees,
                currentPaymentAmount.value.toDouble(), currentDate.time, currentIsFeePaid.value
            )

            FirebaseDatabaseManager.firestoreRef
                .collection("Payments")
                .document(gradeSelected.value)
                .collection(currentStudentId.value)
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

    // ADD LATE FEES
    fun addLateFees() {
        viewModelScope.launch {
            val tuitionFeeOfStudent: StudentFee = StudentFee(currentActiveName.value, currentActiveRollNo.value, PaymentTypes.LateFees,
                currentPaymentAmount.value.toDouble(), currentDate.time, currentIsFeePaid.value
            )

            FirebaseDatabaseManager.firestoreRef
                .collection("Payments")
                .document(gradeSelected.value)
                .collection(currentStudentId.value)
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
    fun selectGrade(grade: String) {
        gradeSelected.value = grade
    }

    // FETCH DATA
    fun fetchStudentFees() {
        viewModelScope.launch {
            FirebaseDatabaseManager.firestoreRef
                .collection("Payments")
                .document(gradeSelected.value)
                .collection(currentStudentId.value)
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




