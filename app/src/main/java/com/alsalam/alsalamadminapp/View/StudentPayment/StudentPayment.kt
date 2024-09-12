package com.alsalam.alsalamadminapp.View.StudentPayment

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.View.Components.CustomTopBar
import com.alsalam.alsalamadminapp.View.Components.SelectCard

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StudentPaymentScreen(navController: NavController) {
    Scaffold(
        topBar = { CustomTopBar(text = "Student Payments") }
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.secondary_gray))
                .padding(all = 10.dp)
        ) {

            item {

                Spacer(modifier = Modifier.height(85.dp))
                SelectCard(heading = "Admission Payments", onClick = {navController.navigate("admissionPaymentScreen")})
                Spacer(modifier = Modifier.height(10.dp))

                SelectCard(heading = "Hostel Payments", onClick = {navController.navigate("hostelPaymentScreen")})
                Spacer(modifier = Modifier.height(10.dp))

                SelectCard(heading = "Tuition Payments", onClick = {navController.navigate("tuitionPaymentScreen")})
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}