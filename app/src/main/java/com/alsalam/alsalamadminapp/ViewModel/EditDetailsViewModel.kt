package com.alsalam.alsalamadminapp.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.alsalam.alsalamadminapp.Firebase.FirebaseDatabaseManager
import com.alsalam.alsalamadminapp.Model.EditStudentInfo
import kotlinx.coroutines.flow.MutableStateFlow

class EditDetailsViewModel: ViewModel() {

    // IMMUTABLE
    var currentClassName: MutableStateFlow<String> = MutableStateFlow<String>("")
    var currentStudentId: MutableStateFlow<String> = MutableStateFlow<String>("")

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
            fathersName.value, motherName.value, village.value, postOffice.value, policeStation.value, district.value, currentClassName.value,
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

