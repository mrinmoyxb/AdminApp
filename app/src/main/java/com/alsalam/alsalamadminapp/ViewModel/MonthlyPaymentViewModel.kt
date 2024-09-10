package com.alsalam.alsalamadminapp.ViewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alsalam.alsalamadminapp.Firebase.FirebaseDatabaseManager
import com.alsalam.alsalamadminapp.Model.MonthlyPayment
import com.alsalam.alsalamadminapp.Model.PaymentTypes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class MonthlyPaymentViewModel: ViewModel() {

    var currentActiveRollNo: MutableStateFlow<String> = MutableStateFlow<String>("")
    var currentActiveName: MutableStateFlow<String> = MutableStateFlow<String>("")
    var currentPaymentAmount: MutableStateFlow<String> = MutableStateFlow<String>("")
    var currentIsFeePaid: MutableStateFlow<Boolean> = MutableStateFlow<Boolean>(false)
    var gradeSelected: MutableStateFlow<String> = MutableStateFlow<String>("")
    var studentId: MutableStateFlow<String> = MutableStateFlow<String>("")

    var fetchGradeSelected: MutableStateFlow<String> = MutableStateFlow<String>("")
    val students: MutableStateFlow<List<MonthlyPayment>> = MutableStateFlow(emptyList())


    private val currentDate = Date()
    @SuppressLint("SimpleDateFormat")
    val formatter = SimpleDateFormat("MM_yyyy")
    private val monthYear: String = formatter.format(Date(currentDate.time))

    fun addMonthlyHostelPayment() {
        val student: MonthlyPayment = MonthlyPayment(currentActiveName.value, gradeSelected.value, currentActiveRollNo.value,
            PaymentTypes.HostelFees, currentPaymentAmount.value.toDouble(), currentDate.time, currentIsFeePaid.value, studentId = studentId.value)
        val dataRef = FirebaseDatabaseManager.monthlyHostelPayment.child(monthYear)
        val studentRef = dataRef.child(studentId.value)

        studentRef.setValue(student)
            .addOnSuccessListener {
                Log.d("StudentAdd", "Student added successfully")
            }
            .addOnFailureListener { exception ->
                Log.e("StudentAddError", "Error adding student: $exception")
            }
    }

    fun addMonthlyAdmissionPayment() {
        val student: MonthlyPayment = MonthlyPayment(currentActiveName.value, gradeSelected.value, currentActiveRollNo.value,
            PaymentTypes.AdmissionFees, currentPaymentAmount.value.toDouble(), currentDate.time, currentIsFeePaid.value, studentId = studentId.value)
        val dataRef = FirebaseDatabaseManager.monthlyAdmissionPayment.child(monthYear)
        val studentRef = dataRef.child(studentId.value)

        studentRef.setValue(student)
            .addOnSuccessListener {
                Log.d("StudentAdd", "Student added successfully")
            }
            .addOnFailureListener { exception ->
                Log.e("StudentAddError", "Error adding student: $exception")
            }
    }

    fun addMonthlyTuitionPayment() {
        val student: MonthlyPayment = MonthlyPayment(currentActiveName.value, gradeSelected.value, currentActiveRollNo.value,
            PaymentTypes.TuitionFees, currentPaymentAmount.value.toDouble(), currentDate.time, currentIsFeePaid.value, studentId = studentId.value)
        val dataRef = FirebaseDatabaseManager.monthlyTuitionPayment.child(monthYear)
        val studentRef = dataRef.child(studentId.value)

        studentRef.setValue(student)
            .addOnSuccessListener {
                Log.d("StudentAdd", "Student added successfully")
            }
            .addOnFailureListener { exception ->
                Log.e("StudentAddError", "Error adding student: $exception")
            }
    }

    fun loadMonthlyHostelPayment() {
        viewModelScope.launch {
            val classRef = FirebaseDatabaseManager.monthlyHostelPayment.child(monthYear)
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

    fun loadMonthlyTuitionPayment() {
        viewModelScope.launch {
            val classRef = FirebaseDatabaseManager.monthlyTuitionPayment.child(monthYear)
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

    fun loadMonthlyAdmissionPayment() {
        viewModelScope.launch {
            val classRef = FirebaseDatabaseManager.monthlyAdmissionPayment.child(monthYear)
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
}

