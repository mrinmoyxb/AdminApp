package com.alsalam.alsalamadminapp.View.FeeScreen.HostelFeeScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.View.Components.CustomTopBar
import com.alsalam.alsalamadminapp.View.Components.StudentDisplayCard
import com.alsalam.alsalamadminapp.View.FeeScreen.CustomHorizontalCard
import com.alsalam.alsalamadminapp.View.FeeScreen.GradeSelectedButton
import com.alsalam.alsalamadminapp.ViewModel.PaymentViewModel
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HostelFeeScreen(navController: NavHostController) {
    val viewModel: PaymentViewModel = viewModel()
    val gradeSelectedByAdmin by viewModel.gradeSelected.collectAsState(0)
    val studentPaymentList by viewModel.studentsPaymentFetched.collectAsState(emptyList())

    var choice by remember { mutableIntStateOf(-1) }
    val context = LocalContext.current
    val formatter = SimpleDateFormat("dd/MM/yy")

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { CustomTopBar(text = "Hostel Fee") })
    {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.secondary_gray))
                .padding(10.dp)
        ) {
            // horizontal Row -> grades
            item {
                Spacer(modifier = Modifier.height(100.dp))

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(12) { item ->
                        CustomHorizontalCard(grade = (item + 1).toString(), onClick = {
                            viewModel.selectGrade(item + 1)
                            //viewModel.fetchHostelFeesPerStudent()
                        })
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

                // grade selected button
                Row(modifier = Modifier.fillMaxWidth()) {
                    GradeSelectedButton(grade = "Grade $gradeSelectedByAdmin", onClick = {})
                    Spacer(modifier = Modifier.width(7.dp))
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            // student list
            item {
                StudentDisplayCard(name = "Student1", roll = "1", dob = "20/10/2002", onClick = {})
                StudentDisplayCard(name = "Student1", roll = "1", dob = "20/10/2002", onClick = {})
                StudentDisplayCard(name = "Student1", roll = "1", dob = "20/10/2002", onClick = {})
            }
        }
    }
}


