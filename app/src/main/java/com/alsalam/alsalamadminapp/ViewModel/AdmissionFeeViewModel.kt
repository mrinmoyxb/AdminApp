package com.alsalam.alsalamadminapp.ViewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.alsalam.alsalamadminapp.Firebase.FirebaseDatabaseManager
import com.alsalam.alsalamadminapp.Model.AdmissionAmount
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.SimpleDateFormat
import java.util.Date

class AdmissionFeeViewModel: ViewModel() {

    var admissionFee: MutableStateFlow<String> = MutableStateFlow<String>("")
    var examFee:MutableStateFlow<String> = MutableStateFlow<String>("")
    var sportsFee: MutableStateFlow<String> = MutableStateFlow<String>("")
    var medicalFee: MutableStateFlow<String> = MutableStateFlow<String>("")
    var estFee: MutableStateFlow<String> = MutableStateFlow<String>("")
    var transportFee: MutableStateFlow<String> = MutableStateFlow<String>("")
    var occasionFee: MutableStateFlow<String> = MutableStateFlow<String>("")
    var bookFee: MutableStateFlow<String> = MutableStateFlow<String>("")
    var miscellaneousFee: MutableStateFlow<String> = MutableStateFlow<String>("")
    var lateFee: MutableStateFlow<String> = MutableStateFlow<String>("")
    var electricityFee: MutableStateFlow<String> = MutableStateFlow<String>("")
    var othersFee: MutableStateFlow<String> = MutableStateFlow<String>("")

    var totalFee: MutableStateFlow<Double> = MutableStateFlow<Double>(0.0)
    var gradeSelected: MutableStateFlow<Int> = MutableStateFlow<Int>(-1)
    var currentActiveRollNo: MutableStateFlow<String> = MutableStateFlow<String>("")

    private val currentDate = Date()
    @SuppressLint("SimpleDateFormat")
    val formatter = SimpleDateFormat("dd_MM_yyyy")
    private val newDate: String = formatter.format(Date(currentDate.time))

    // Calculate Total Bill
    fun totalBill() {
        val fees = listOf(
            admissionFee.value.toDoubleOrNull(),
            examFee.value.toDoubleOrNull(),
            sportsFee.value.toDoubleOrNull(),
            medicalFee.value.toDoubleOrNull(),
            estFee.value.toDoubleOrNull(),
            transportFee.value.toDoubleOrNull(),
            occasionFee.value.toDoubleOrNull(),
            bookFee.value.toDoubleOrNull(),
            miscellaneousFee.value.toDoubleOrNull(),
            lateFee.value.toDoubleOrNull(),
            electricityFee.value.toDoubleOrNull(),
            othersFee.value.toDoubleOrNull()
        )

        val total = fees.filterNotNull().sum()
        totalFee.value = total
    }

    // Reset Bill
    fun resetBill(){
        admissionFee.value = ""
        examFee.value = ""
        sportsFee.value = ""
        medicalFee.value = ""
        estFee.value = ""
        transportFee.value = ""
        occasionFee.value = ""
        bookFee.value = ""
        miscellaneousFee.value = ""
        lateFee.value = ""
        electricityFee.value = ""
        othersFee.value = ""
    }

    fun addAdmissionFees() {
        val database = FirebaseDatabaseManager.database
        val student2Ref = database.getReference("Payments/${gradeSelected.value.toString()}/${currentActiveRollNo.value.toString()}")
        val updateMap = hashMapOf<String, Any>("Admission_$newDate" to AdmissionAmount(totalFee.value, currentDate.time, true))
        student2Ref.updateChildren(updateMap)
            .addOnSuccessListener {
                resetBill()
                Log.d("Firebase", "record updated successfully!")
            }
            .addOnFailureListener { exception ->
                Log.w("Firebase", "Error updating payment record: $exception")
            }
    }


}

