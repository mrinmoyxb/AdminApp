package com.alsalam.alsalamadminapp.View.StudentPayment

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.View.Components.CustomTopBar
import com.alsalam.alsalamadminapp.View.Components.StudentCardForAdminPayment
import com.alsalam.alsalamadminapp.ViewModel.AdminApprovePaymentViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdmissionAdminScreen(viewModel: AdminApprovePaymentViewModel){

    val students by viewModel.students.observeAsState(emptyList())
    val monthSelected by viewModel.monthSelected.collectAsState("")
    val context = LocalContext.current

    Scaffold(
        topBar = { CustomTopBar(text = monthSelected) }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.secondary_gray))
                .padding(all = 10.dp)
        )
        {
            item {
                Spacer(modifier = Modifier.height(85.dp))

                if (students.isEmpty()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("No data available")
                    }
                } else {
                    students.forEach { student ->
                        StudentCardForAdminPayment(
                            name = student.studentName,
                            studentId = student.studentId,
                            grade = student.studentGrade,
                            roll = student.studentRollNo,
                            feesPaid = student.studentFeesPaid,
                            approvedByAdmin = student.approveByAdmin,
                            onClick = {
                                viewModel.studentIdSelected.value = student.studentId
                                viewModel.updateAdmissionPayment()
                                Toast.makeText(context, "Approved", Toast.LENGTH_SHORT).show()
                            })
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

            }

        }
    }
}
