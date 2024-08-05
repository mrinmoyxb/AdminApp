package com.alsalam.alsalamadminapp.ViewModel.DailyTrackingViewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alsalam.alsalamadminapp.Firebase.FirebaseDatabaseManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class DeleteCollection: ViewModel() {


    fun deleteMonthlyAdmissionPayment(onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            val database = FirebaseDatabaseManager.database
            val collectionRef = database.getReference("MonthlyAdmissionPayment")

            collectionRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (child in dataSnapshot.children) {
                        child.ref.removeValue()
                    }
                    onComplete(true)
                }

                override fun onCancelled(error: DatabaseError) {
                    onComplete(false)
                }
            })
        }
    }

    fun deleteMonthlyHostelPayment(onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            val database = FirebaseDatabaseManager.database
            val collectionRef = database.getReference("MonthlyHostelPayment")

            collectionRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (child in dataSnapshot.children) {
                        child.ref.removeValue()
                    }
                    onComplete(true)
                }

                override fun onCancelled(error: DatabaseError) {
                    onComplete(false)
                }
            })
        }
    }

    fun deleteMonthlyTuitionPayment(onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            val database = FirebaseDatabaseManager.database
            val collectionRef = database.getReference("MonthlyTuitionPayment")

            collectionRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (child in dataSnapshot.children) {
                        child.ref.removeValue()
                    }
                    onComplete(true)
                }

                override fun onCancelled(error: DatabaseError) {
                    onComplete(false) // Indicate failure
                }
            })
        }
    }

    fun deleteDailyCollection(onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            val database = FirebaseDatabaseManager.database
            val collectionRef = database.getReference("DailyCollection")

            collectionRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (child in dataSnapshot.children) {
                        child.ref.removeValue()
                    }
                    onComplete(true)
                }

                override fun onCancelled(error: DatabaseError) {
                    onComplete(false)
                }
            })
        }
    }

    fun deletePayments(onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            val database = FirebaseDatabaseManager.database
            val collectionRef = database.getReference("Payments")

            collectionRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (child in dataSnapshot.children) {
                        child.ref.removeValue()
                    }
                    onComplete(true)
                }

                override fun onCancelled(error: DatabaseError) {
                    onComplete(false)
                }
            })
        }
    }


    fun deleteEntireCollection() {
        viewModelScope.launch {
            val db = FirebaseFirestore.getInstance()
            val paymentsCollection = db.collection("Payments")

            paymentsCollection.get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        document.reference.delete()
                            .addOnSuccessListener {
                                Log.d(TAG, "Document ${document.id} successfully deleted!") }
                            .addOnFailureListener { e ->
                                Log.w(TAG, "Error deleting document ${document.id}", e)
                            }
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents: ", exception)
                }
        }
    }

}
