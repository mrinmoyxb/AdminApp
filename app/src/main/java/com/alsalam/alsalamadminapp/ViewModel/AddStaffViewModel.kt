package com.alsalam.alsalamadminapp.ViewModel

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alsalam.alsalamadminapp.Firebase.FirebaseDatabaseManager
import com.alsalam.alsalamadminapp.Model.Designation
import com.alsalam.alsalamadminapp.Model.StudentInfo
import com.alsalam.alsalamadminapp.Model.Subjects
import com.alsalam.alsalamadminapp.Model.Teacher
import com.alsalam.alsalamadminapp.Model.randomStringGenerator
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Random

class AddStaffViewModel: ViewModel(){
    // section 1 -> basic details
    var teacherName: MutableStateFlow<String> = MutableStateFlow<String>("")
    var subject: MutableStateFlow<Subjects> = MutableStateFlow<Subjects>(Subjects.NULL)
    val designation: MutableStateFlow<Designation> = MutableStateFlow<Designation>(Designation.NULL)
    var salary: MutableStateFlow<String> = MutableStateFlow<String>("")
    var addressOfTeacher: MutableStateFlow<String> = MutableStateFlow<String>("")
    var dateOfAppointment: MutableStateFlow<String> = MutableStateFlow<String>("")
    var qualification: MutableStateFlow<String> = MutableStateFlow<String>("")
    var bioData: MutableStateFlow<String> = MutableStateFlow<String>("")

    // section 2 -> image section
    var teacherImage: MutableLiveData<Uri?> = MutableLiveData()
    var uploadProgress: MutableLiveData<Int> = MutableLiveData(0)

    // date
    @SuppressLint("SimpleDateFormat")
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    val currentDate = Date()

    // teacher fetched
    val teacher: MutableStateFlow<List<Teacher>> = MutableStateFlow(emptyList())


    fun addTeacherBySubject() {
        val teacher: Teacher = Teacher(teacherName.value, subject.value, designation.value, salary.value.toDouble(), addressOfTeacher.value, dateOfAppointment.value, qualification.value, bioData.value)
        val teacherRef = FirebaseDatabaseManager.teacherRef.child(subject.value.toString())
        val studentRef = teacherRef.child("${subject.value}_${teacherName.value}")

        studentRef.setValue(teacher)
            .addOnSuccessListener {
                reset()
                Log.d("Teacher Added", "Teacher added successfully")
            }
            .addOnFailureListener { exception ->
                Log.e("Error", "Error adding teacher: $exception")
            }
    }

    fun loadTeachers() {
        viewModelScope.launch {
            val classRef = FirebaseDatabaseManager.teacherRef.child(subject.value.toString())
            classRef.get().addOnSuccessListener { dataSnapshot ->
                val studentsList = mutableListOf<Teacher>()
                dataSnapshot.children.forEach { child ->
                    val student = child.getValue(Teacher::class.java)
                    student?.let { studentsList.add(it) }
                }
                teacher.value = studentsList
            }.addOnFailureListener { exception ->
                Log.e("LoadStudentsError", "Failed to load students: ${exception.message}")
            }
        }
    }


    // image
    fun handleImageUri(uri: Uri?) {
        teacherImage.value = uri
    }

    // Reset all the values
    fun reset() {
        teacherName.value = ""
        subject.value = Subjects.NULL
        designation.value = Designation.NULL
        salary.value = ""
        addressOfTeacher.value = ""
        dateOfAppointment.value = ""
        qualification.value = ""
        bioData.value = ""
    }
}