package com.alsalam.alsalamadminapp.Firebase

import android.annotation.SuppressLint
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

object FirebaseDatabaseManager {

    // database reference
    val database = Firebase.database
    val classRef = database.getReference("Students")
    val allStudentRef = database.getReference("AllStudents")
    val teacherRef = database.getReference("Teachers")
    val dailyCollection = database.getReference("DailyCollection")
    val dailyExpenditure = database.getReference("DailyExpenditure")

    val balanceRef = database.getReference("Balance")

    // storage reference
    val storageRef: StorageReference = FirebaseStorage.getInstance().reference

    // firestore reference
    @SuppressLint("StaticFieldLeak")
    val firestoreRef = FirebaseFirestore.getInstance()
}