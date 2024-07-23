package com.alsalam.alsalamadminapp.ViewModel

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alsalam.alsalamadminapp.Firebase.FirebaseDatabaseManager
import com.alsalam.alsalamadminapp.Model.PaymentTypes
import com.alsalam.alsalamadminapp.Model.StudentFee
import com.alsalam.alsalamadminapp.Model.StudentInfo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class AddStudentViewModel: ViewModel() {

    // section 1 -> basic details
    var studentName: MutableStateFlow<String> = MutableStateFlow<String>("")
    var studentRollNo: MutableStateFlow<String> = MutableStateFlow<String>("")
    var studentDateOfBirth: MutableStateFlow<String> = MutableStateFlow<String>("")
    var className: MutableStateFlow<String> = MutableStateFlow<String>("")

    // section 2 -> image section
    var studentImage: MutableLiveData<Uri?> = MutableLiveData()
    var uploadProgress: MutableLiveData<Int> = MutableLiveData(0)

    // Students fetched from database
    var gradeSelected: MutableStateFlow<Int> = MutableStateFlow(0)
    var _gradeSelected: StateFlow<Int> = gradeSelected
    private val _students = MutableLiveData<List<StudentInfo>>()
    val students: LiveData<List<StudentInfo>> = _students

    // Others
    val currentDate = Date()
    @SuppressLint("SimpleDateFormat")
    val formatter = SimpleDateFormat("dd_MM_yyyy")
    private val newDate: String = formatter.format(Date(currentDate.time))


    // ADD STUDENT INFO:
    // Primary Key = Roll.No
    fun addStudentByRollNo() {
        val student: StudentInfo = StudentInfo(studentName.value, studentRollNo.value, studentDateOfBirth.value)
        val classRef = FirebaseDatabaseManager.classRef.child(className.value)
        val studentRef = classRef.child(studentRollNo.value)

        studentRef.setValue(student)
            .addOnSuccessListener {
                if (studentImage.value != null) {
                    uploadStudentImage(studentRollNo.value) { imageUrl ->
                        student.imageUrl = imageUrl
                        studentRef.updateChildren(mapOf("imageUrl" to imageUrl))
                    }
                }
                reset()
                Log.d("StudentAdd", "Student added successfully with roll number: $studentRollNo")
            }
            .addOnFailureListener { exception ->
                Log.e("StudentAddError", "Error adding student: $exception")
            }
    }


    // image
    fun handleImageUri(uri: Uri?) {
        studentImage.value = uri
    }

    private fun uploadStudentImage(studentId: String, onSuccess: (String) -> Unit) {
        viewModelScope.launch {
            val imageFile = studentImage.value ?: return@launch
            val imageRef = FirebaseDatabaseManager.storageRef.child("StudentImages/$studentId.jpg")
            val uploadTask = imageRef.putFile(imageFile)

            uploadTask.addOnProgressListener { taskSnapshot ->
                val progress =
                    (100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount).toInt()
                uploadProgress.postValue(progress)
            }.addOnSuccessListener { taskSnapshot ->
                taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->
                    onSuccess(uri.toString())
                }
            }.addOnFailureListener { exception ->
                Log.e("ImageUploadError", "Image upload failed: ${exception.message}")
            }
        }
    }


    // grade selected
    fun onGradeSelected(grade: Int) {
        gradeSelected.value = grade
    }


    // Fetch students from database based on class
    fun loadStudents() {
        viewModelScope.launch {
            val classRef = FirebaseDatabaseManager.classRef.child(gradeSelected.value.toString())
            classRef.get().addOnSuccessListener { dataSnapshot ->
                val studentsList = mutableListOf<StudentInfo>()
                dataSnapshot.children.forEach { child ->
                    val student = child.getValue(StudentInfo::class.java)
                    student?.let { studentsList.add(it) }
                }
                _students.value = studentsList
            }.addOnFailureListener { exception ->
                Log.e("LoadStudentsError", "Failed to load students: ${exception.message}")
            }
        }
    }


    // Reset all the values
    fun reset() {
        studentName.value = ""
        studentRollNo.value = ""
        studentDateOfBirth.value = ""
        className.value = ""
    }
}

