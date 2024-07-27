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
import com.alsalam.alsalamadminapp.View.FeeScreen.GradeSelectedButton
import com.alsalam.alsalamadminapp.ViewModel.TeacherSalaryViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun TeacherDetailSalary(navController: NavHostController, teacherSalaryViewModel: TeacherSalaryViewModel){
    Scaffold(floatingActionButton = { FloatingButton(navController = navController, route = "AddSalaryScreen")}) {
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
                        .height(180.dp),
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
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(teacherSalaryViewModel.currentTeacherQualification.value, color = Color.White, fontWeight = FontWeight.Normal, fontSize = 20.sp)
                            Text(teacherSalaryViewModel.currentTeacherSubject.value.toString(), color = Color.White, fontWeight = FontWeight.Normal, fontSize = 20.sp)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))

                // Salary
                GradeSelectedButton(grade = "Salary", onClick = {})
                Spacer(modifier = Modifier.height(10.dp))

                // Months


            }
        }
    }
}

@Composable
fun MonthCard(grade: String, onClick: () -> Unit){

    val context = LocalContext.current

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .background(Color.Transparent)
        .clickable {
            onClick()
        },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(colorResource(id = R.color.secondary_blue)))
    {
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center)
        {
            Text(
                grade,
                fontSize=20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
    }
}