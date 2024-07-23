package com.alsalam.alsalamadminapp.ViewModel

import android.annotation.SuppressLint
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alsalam.alsalamadminapp.Firebase.FirebaseDatabaseManager
import com.alsalam.alsalamadminapp.Model.StudentResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.UUID

class PdfUploadAndRetrieveViewModel: ViewModel() {

        private val _selectedPdfUri = MutableLiveData<Uri?>()
        val selectedPdfUri: LiveData<Uri?> = _selectedPdfUri

        private val _uploadProgress = MutableLiveData<Int>()
        val uploadProgress: LiveData<Int> = _uploadProgress

        private val _uploadStatus = MutableLiveData<UploadStatus>()
        val uploadStatus: LiveData<UploadStatus> = _uploadStatus

        val fileName = MutableStateFlow<String>("")

        // Result
        private val currentDate = Date()
        @SuppressLint("SimpleDateFormat")
        val formatter = SimpleDateFormat("dd_MM_yyyy")
        private val newDate: String = formatter.format(Date(currentDate.time))
        val gradeSelected: MutableStateFlow<Int> = MutableStateFlow<Int>(0)
        val currentStudentName: MutableStateFlow<String> = MutableStateFlow<String>("")
        val currentActiveRollNo: MutableStateFlow<String> = MutableStateFlow<String>("")


        fun selectPdf(uri: Uri?) {
            _selectedPdfUri.value = uri
            val pdfUri = _selectedPdfUri.value ?: return
            val file = File(pdfUri.path!!)
            fileName.value = File(pdfUri.path!!).nameWithoutExtension
        }

        // NOTICE
        fun uploadNoticePdf() {
            val pdfUri = _selectedPdfUri.value ?: return
            val file = File(pdfUri.path!!)

            viewModelScope.launch {
                _uploadStatus.value = UploadStatus.UPLOADING
                val uploadTask = FirebaseDatabaseManager.storageRef.child("Notice/${UUID.randomUUID()}.pdf").putFile(pdfUri)
                uploadTask.addOnProgressListener { taskSnapshot ->
                    val progress = (100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount).toInt()
                    _uploadProgress.value = progress
                }.addOnSuccessListener {
                    _uploadStatus.value = UploadStatus.SUCCESS
                }.addOnFailureListener {
                    _uploadStatus.value = UploadStatus.FAILURE
                }
            }
        }

        // CLASS ROUTINE
        fun uploadClassRoutinePdf() {
            val pdfUri = _selectedPdfUri.value ?: return
            val file = File(pdfUri.path!!)

            viewModelScope.launch {
                _uploadStatus.value = UploadStatus.UPLOADING
                val uploadTask = FirebaseDatabaseManager.storageRef.child("ClassRoutine/${UUID.randomUUID()}.pdf").putFile(pdfUri)
                uploadTask.addOnProgressListener { taskSnapshot ->
                    val progress = (100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount).toInt()
                    _uploadProgress.value = progress
                }.addOnSuccessListener {
                    _uploadStatus.value = UploadStatus.SUCCESS
                }.addOnFailureListener {
                    _uploadStatus.value = UploadStatus.FAILURE
                }
            }
        }

    // HOLIDAY
    fun uploadHolidayPdf() {
        val pdfUri = _selectedPdfUri.value ?: return
        val file = File(pdfUri.path!!)

        viewModelScope.launch {
            _uploadStatus.value = UploadStatus.UPLOADING
            val uploadTask = FirebaseDatabaseManager.storageRef.child("Holiday/${UUID.randomUUID()}.pdf").putFile(pdfUri)
            uploadTask.addOnProgressListener { taskSnapshot ->
                val progress = (100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount).toInt()
                _uploadProgress.value = progress
            }.addOnSuccessListener {
                _uploadStatus.value = UploadStatus.SUCCESS
            }.addOnFailureListener {
                _uploadStatus.value = UploadStatus.FAILURE
            }
        }
    }

    // RESULT
    fun uploadResultPdf() {
        val pdfUri = _selectedPdfUri.value ?: return
        val file = File(pdfUri.path!!)

        viewModelScope.launch {
            _uploadStatus.value = UploadStatus.UPLOADING
            val uploadTask = FirebaseDatabaseManager.storageRef.child("Result/${UUID.randomUUID()}.pdf").putFile(pdfUri)
            uploadTask.addOnProgressListener { taskSnapshot ->
                val progress = (100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount).toInt()
                _uploadProgress.value = progress
            }.addOnSuccessListener {
                _uploadStatus.value = UploadStatus.SUCCESS
            }.addOnFailureListener {
                _uploadStatus.value = UploadStatus.FAILURE
            }
        }
    }


    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    // UPLOAD STUDENT RESULT
    fun selectPdfAndUpload() {
        val pdfUri = _selectedPdfUri.value ?: return
        val fileName = "Result_${newDate.toString()}_${gradeSelected.value.toString()}_$currentActiveRollNo.pdf"

        viewModelScope.launch {
            _uploadStatus.value = UploadStatus.UPLOADING
            val uploadTask = FirebaseDatabaseManager.storageRef.child("Results/${gradeSelected.value}/${fileName}").putFile(pdfUri)
            uploadTask.addOnProgressListener { taskSnapshot ->
                val progress = (100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount).toInt()
                _uploadProgress.value = progress
            }.addOnSuccessListener { taskSnapshot ->
                val downloadUrl = taskSnapshot.metadata?.reference?.downloadUrl.toString()
                val student = StudentResult(
                    nameOfStudent = currentStudentName.value,
                    rollNo = currentActiveRollNo.value,
                    pdfUrl = downloadUrl
                )
                // Update database with student data including the URL
                val database = FirebaseDatabaseManager.database
                val student2ref = database.getReference("Results/${gradeSelected.value.toString()}/${currentActiveRollNo.value.toString()}")
                student2ref.updateChildren(mapOf("Result_$newDate" to student))
                    .addOnSuccessListener {
                        _uploadStatus.value = UploadStatus.SUCCESS
                    }
                    .addOnFailureListener {
                        _uploadStatus.value = UploadStatus.FAILURE
                    }
            }.addOnFailureListener {
                _uploadStatus.value = UploadStatus.FAILURE
            }
        }
    }


    enum class UploadStatus {
            IDLE, UPLOADING, SUCCESS, FAILURE
        }

}