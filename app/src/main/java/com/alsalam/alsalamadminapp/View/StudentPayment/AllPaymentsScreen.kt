package com.alsalam.alsalamadminapp.View.StudentPayment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alsalam.alsalamadminapp.ViewModel.AdminApprovePaymentViewModel

@Composable
fun AllPaymentScreen(viewModel: AdminApprovePaymentViewModel){
    val students by viewModel.students.observeAsState(emptyList())
    val monthSelected by viewModel.monthSelected.collectAsState("")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ){
        Text("Payments")
        Text(monthSelected)
        students.forEach{student ->
            Text("pay")
            Text("name: ${student.studentName}")
            Text("paid: ${student.studentFeesPaid}")
            //Text("approve: ${student.approveByAdmin}")

            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}