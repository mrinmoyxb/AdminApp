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
import com.alsalam.alsalamadminapp.ViewModel.AdminApprovePaymentViewModel
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SimpleDateFormat")
@Composable
fun HostelPaymentScreen(navController: NavController, adminApprovePaymentViewModel: AdminApprovePaymentViewModel){

    val temp: List<String> = listOf("01_2024", "02_2024", "03_20204", "04_2024", "05_2024", "06_2024", "07_20204", "08_2024", "09_2024", "10_20204", "11_2024", "12_2024")

    Scaffold(
        topBar = { CustomTopBar(text = "Hostel Payments")}
    ){
        LazyColumn(modifier = Modifier.fillMaxSize()
            .background(colorResource(id = R.color.secondary_gray))
            .padding(all = 10.dp))
        {
            item{
                Spacer(modifier = Modifier.height(85.dp))
                temp.forEach{ month ->
                    SelectCard(heading = month, onClick = {
                        navController.navigate("allPaymentsToApprove")
                        adminApprovePaymentViewModel.monthSelected.value = month
                        adminApprovePaymentViewModel.loadMonthlyHostelPayment()
                    })
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}