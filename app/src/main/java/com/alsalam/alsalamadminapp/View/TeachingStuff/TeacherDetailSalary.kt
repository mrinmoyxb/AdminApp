package com.alsalam.alsalamadminapp.View.TeachingStuff

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.View.Components.FloatingButton
import com.alsalam.alsalamadminapp.View.Components.GradeCard
import com.alsalam.alsalamadminapp.View.Components.SaveUploadButton
import com.alsalam.alsalamadminapp.View.FeeScreen.FeesPaidStudentCard
import com.alsalam.alsalamadminapp.View.FeeScreen.GradeSelectedButton
import com.alsalam.alsalamadminapp.ViewModel.TeacherSalaryViewModel
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition",
    "SimpleDateFormat"
)
@Composable
fun TeacherDetailSalary(navController: NavHostController, teacherSalaryViewModel: TeacherSalaryViewModel){

    val salaryList by teacherSalaryViewModel.salaryList.collectAsState(emptyList())
    val formatter = SimpleDateFormat("dd/MM/yyyy")

    Scaffold(floatingActionButton = { FloatingButton(navController = navController, route = "AddSalaryScreen")}) {

        LaunchedEffect(Unit) {
            teacherSalaryViewModel.fetchTeacherSalary()
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.secondary_gray1))
                .padding(10.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(45.dp))

                // Teacher Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(10.dp),
                    colors = CardDefaults.cardColors(colorResource(id = R.color.secondary_blue))
                )
                {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp)
                    ) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Spacer(modifier = Modifier.height(30.dp))
                            Text(teacherSalaryViewModel.currentTeacherName.value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 25.sp)
                            Text(teacherSalaryViewModel.currentTeacherQualification.value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                            Text("Subject: ${teacherSalaryViewModel.currentTeacherSubject.value.toString()}", color = Color.White, fontWeight = FontWeight.Normal, fontSize = 20.sp)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))

                // Salary
                GradeSelectedButton(grade = "Salary Paid", onClick = {})
                Spacer(modifier = Modifier.height(10.dp))

                // Months
                salaryList.forEach { s ->
                    SalaryPaidCard(
                        month = s.month,
                        name = s.teacherName,
                        amount = s.salary.toString(),
                        paid = true,
                        date = formatter.format(Date(s.date))
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }

            }
        }
    }
}



@Composable
fun SalaryPaidCard(month: String, name: String, amount: String, paid: Boolean, date: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(colorResource(id = R.color.secondary_blue))
    ) {
        Row(modifier = Modifier.fillMaxSize().padding(all = 10.dp)) {
            Column(
                modifier = Modifier
                    .width(270.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(month, color = Color.White, fontWeight = FontWeight.Medium, fontSize = 18.sp, maxLines = 1)
                Text(name.uppercase(), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 22.sp, maxLines = 1)
                Text("₹ $amount", color = Color.White, fontWeight = FontWeight.Normal, fontSize = 22.sp, maxLines = 1)
                Spacer(modifier = Modifier.height(8.dp))

            }

            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(if (paid) "PAID" else "NOT PAID", color = if (paid) Color.Green else Color.Red, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(if (paid) date else "", color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
            }
        }
    }
}

