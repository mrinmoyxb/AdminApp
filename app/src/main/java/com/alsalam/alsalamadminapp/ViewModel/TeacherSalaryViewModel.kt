package com.alsalam.alsalamadminapp.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alsalam.alsalamadminapp.Firebase.FirebaseDatabaseManager
import com.alsalam.alsalamadminapp.Model.StudentFee
import com.alsalam.alsalamadminapp.Model.Subjects
import com.alsalam.alsalamadminapp.Model.TeacherSalary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TeacherSalaryViewModel: ViewModel() {

    var currentMonth: MutableStateFlow<String> = MutableStateFlow<String>("")
    var currentTeacherName: MutableStateFlow<String> = MutableStateFlow<String>("")
    var currentTeacherSubject: MutableStateFlow<Subjects> = MutableStateFlow<Subjects>(Subjects.NULL)
    var currentTeacherSalary: MutableStateFlow<String> = MutableStateFlow<String>("")
    var currentTeacherQualification: MutableStateFlow<String> = MutableStateFlow<String>("")

   var salaryList: MutableStateFlow<List<TeacherSalary>> = MutableStateFlow<List<TeacherSalary>>(emptyList())

    fun addTeacherSalary() {
        viewModelScope.launch {
            val teacherSalary: TeacherSalary = TeacherSalary(currentTeacherName.value, currentTeacherSubject.value, currentTeacherSalary.value.toDouble(), currentTeacherQualification.value, currentMonth.value)

            FirebaseDatabaseManager.firestoreRef
                .collection("TeachersSalary")
                .document("${currentTeacherSubject.value}")
                .collection("${currentTeacherSubject.value}_${currentTeacherName.value}")
                .add(teacherSalary)
                .addOnSuccessListener {
                    currentTeacherSalary.value = ""
                    Log.d("Firebase FireStore Success", "record updated successfully!")
                }
                .addOnFailureListener {
                    Log.d("Firebase FireStore Failed", "record updated successfully!")
                }
        }
    }

    fun fetchTeacherSalary() {
        viewModelScope.launch {
            FirebaseDatabaseManager.firestoreRef
                .collection("TeachersSalary")
                .document("${currentTeacherSubject.value}")
                .collection("${currentTeacherSubject.value}_${currentTeacherName.value}")
                .get()
                .addOnSuccessListener { result ->
                    val fetchedData = result.toObjects(TeacherSalary::class.java)
                    salaryList.value = fetchedData
                }
                .addOnFailureListener { e ->
                    Log.w("FireStore ERROR", "Error fetching documents", e)
                }
        }
    }
}