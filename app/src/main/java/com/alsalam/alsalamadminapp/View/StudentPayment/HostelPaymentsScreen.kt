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

    val monthList: List<String> = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
    val temp: List<String> = listOf("08_2024", "09_2024")

    val currentDate = Date()
    val formatter = SimpleDateFormat("yyyy")
    val newDate: String = formatter.format(Date(currentDate.time))

    Scaffold(
        topBar = { CustomTopBar(text = "Student Payments")}
    ){
        LazyColumn(modifier = Modifier.fillMaxSize()
            .background(colorResource(id = R.color.secondary_gray))
            .padding(all = 10.dp))
        {
            item{
                Spacer(modifier = Modifier.height(85.dp))
                temp.forEach{ month ->
                    SelectCard(heading = "$month $newDate", onClick = {
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