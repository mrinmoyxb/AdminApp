package com.alsalam.alsalamadminapp.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alsalam.alsalamadminapp.Firebase.FirebaseDatabaseManager
import com.alsalam.alsalamadminapp.Model.Balance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BalanceViewModel: ViewModel() {

    val collection: MutableStateFlow<Double> = MutableStateFlow<Double>(0.0)
    val expense: MutableStateFlow<Double> = MutableStateFlow<Double>(0.0)

    val currentBalance: MutableStateFlow<Double> = MutableStateFlow<Double>(0.0)

    init {
        fetchBalanceFromFirebase()
    }

    fun addMoney(amount: Double){
        viewModelScope.launch {
            collection.value += amount
            updateBalance()
        }
    }

    fun deductMoney(amount: Double){
        viewModelScope.launch {
            expense.value += amount
            updateBalance()
        }
    }

    private fun updateBalance() {
        viewModelScope.launch {
            val newBalance = collection.value - expense.value
            val addMoney: Balance = Balance(collection.value)
            val balanceRef = FirebaseDatabaseManager.balanceRef.child("UpdateBalance")
            balanceRef.setValue(Balance(newBalance))
                .addOnSuccessListener {
                    currentBalance.value = newBalance
                    Log.d("Firebase", "Balance updated successfully")
                }
                .addOnFailureListener { exception ->
                    Log.e("Firebase", "Failed to update balance: ${exception.message}")
                }

        }
    }

    private fun fetchBalanceFromFirebase() {
        val balanceRef = FirebaseDatabaseManager.balanceRef.child("UpdateBalance")
        balanceRef.get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val balance = snapshot.getValue(Balance::class.java)?.amount ?: 0.0
                currentBalance.value = balance
                collection.value = balance
            } else {
                collection.value = 0.0
                currentBalance.value = 0.0
            }
        }.addOnFailureListener { exception ->
            Log.e("Firebase", "Failed to fetch balance: ${exception.message}")
        }
    }
}

