package com.alsalam.alsalamadminapp.View.FeeScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.View.Components.SelectCard
import com.alsalam.alsalamadminapp.ViewModel.DailyTrackingViewModel.DeleteCollection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Composable
fun FeeScreen(navController: NavHostController){

    val viewModel: DeleteCollection = viewModel()
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.secondary_gray))
            .padding(10.dp)
    ){
        SelectCard(heading = "Daily Tracking", onClick = {navController.navigate("dailyTrackingScreen")})
        Spacer(modifier = Modifier.height(10.dp))

        SelectCard(heading = "Admission Fees", onClick = {navController.navigate("admissionFees")})
        Spacer(modifier = Modifier.height(10.dp))

        SelectCard(heading = "Tuition Fees", onClick = {navController.navigate("tuitionFees")})
        Spacer(modifier = Modifier.height(10.dp))

        SelectCard(heading = "Hostel Fees", onClick = {navController.navigate("hostelFees")})
        Spacer(modifier = Modifier.height(10.dp))

        SelectCard(heading = "Student Payments", onClick = {navController.navigate("studentPayments")})
        Spacer(modifier = Modifier.height(30.dp))

        SelectCard(heading = "Delete Payments", color = R.color.colorRed, onClick = {
            viewModel.deleteMonthlyHostelPayment {  }
            viewModel.deleteMonthlyTuitionPayment {  }
            viewModel.deleteMonthlyAdmissionPayment {  }
            viewModel.deleteDailyCollection {  }
            viewModel.deletePayments {  }
            viewModel.deleteEntireCollection()
            Toast.makeText(context, "Deleted Payments from Database", Toast.LENGTH_SHORT).show()
        })
    }

}