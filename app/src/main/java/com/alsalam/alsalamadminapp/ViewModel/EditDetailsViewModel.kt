package com.alsalam.alsalamadminapp.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.alsalam.alsalamadminapp.Firebase.FirebaseDatabaseManager
import kotlinx.coroutines.flow.MutableStateFlow

class EditDetailsViewModel: ViewModel() {

    // IMMUTABLE
    var currentClassName: MutableStateFlow<String> = MutableStateFlow<String>("")
    var currentStudentId: MutableStateFlow<String> = MutableStateFlow<String>("")
    var currentgradeSelected: MutableStateFlow<String> = MutableStateFlow<String>("")

    // MUTABLE
    var studentName: MutableStateFlow<String> = MutableStateFlow<String>("")
    var studentRollNo: MutableStateFlow<String> = MutableStateFlow<String>("")
    var studentDateOfBirth: MutableStateFlow<String> = MutableStateFlow<String>("")
    var motherName: MutableStateFlow<String> = MutableStateFlow<String>("")
    var fathersName: MutableStateFlow<String> = MutableStateFlow<String>("")
    var village: MutableStateFlow<String> = MutableStateFlow<String>("")
    var postOffice: MutableStateFlow<String> = MutableStateFlow<String>("")
    var policeStation: MutableStateFlow<String> = MutableStateFlow<String>("")
    var district : MutableStateFlow<String> = MutableStateFlow<String>("")
    var pin: MutableStateFlow<String> = MutableStateFlow<String>("")
    var mobileNo: MutableStateFlow<String> = MutableStateFlow<String>("")
    var admissionDate: MutableStateFlow<String> = MutableStateFlow<String>("")
    var admissionFees: MutableStateFlow<String> = MutableStateFlow<String>("")
    var monthlyFees: MutableStateFlow<String> = MutableStateFlow<String>("")


    fun updateStudent() {
        val studentRef = FirebaseDatabaseManager.classRef.child(currentClassName.value).child(currentStudentId.value)
        val updatedStudent = EditStudentInfo(studentName.value, studentRollNo.value, studentDateOfBirth.value,
            fathersName.value, motherName.value, village.value, postOffice.value, policeStation.value, district.value, currentgradeSelected.value,
            pin.value, mobileNo.value, admissionDate.value, admissionFees.value, monthlyFees.value)

        studentRef.updateChildren(updatedStudent.toMap())
            .addOnSuccessListener {
                Log.d("StudentAdd", "Student added successfully with roll number: $studentRollNo")
            }
            .addOnFailureListener {
                Log.d("Failed", "Error")
            }
    }
}





// STUDENT----------------------------------------------------------------------------------------//
data class EditStudentInfo(
    val studentName: String,
    val rollNo: String,
    val dateOfBirth: String,
    var fatherName: String,
    var motherName: String,
    var village: String,
    var postOffice: String,
    var policeStation: String,
    var district: String,
    var grade: String,
    var pin: String,
    var mobileNo: String,
    var admissionDate: String,
    var admissionFees: String,
    var monthlyFees: String,

    ){
    constructor() : this("", "","", "", "", "", "", "", "", "", "", "", "", "", "")
    fun toMap(): Map<String, Any> {
        return mapOf(
            "studentName" to studentName,
            "rollNo" to rollNo,
            "dateOfBirth" to dateOfBirth,
            "fatherName" to fatherName,
            "motherName" to motherName,
            "village" to village,
            "postOffice" to postOffice,
            "policeStation" to policeStation,
            "district" to district,
            "grade" to grade,
            "pin" to pin,
            "mobileNo" to mobileNo,
            "admissionDate" to admissionDate,
            "admissionFees" to admissionFees,
            "monthlyFees" to monthlyFees
        )
    }
}