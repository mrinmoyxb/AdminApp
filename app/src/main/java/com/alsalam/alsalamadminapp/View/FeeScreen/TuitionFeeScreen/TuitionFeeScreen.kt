package com.alsalam.alsalamadminapp.View.FeeScreen.TuitionFeeScreen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.View.Components.CustomTopBar
import com.alsalam.alsalamadminapp.View.Components.FloatingButton
import com.alsalam.alsalamadminapp.View.FeeScreen.CustomHorizontalCard
import com.alsalam.alsalamadminapp.View.FeeScreen.FeesPaidStudentCard
import com.alsalam.alsalamadminapp.View.FeeScreen.GradeSelectedButton
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SimpleDateFormat")
@Composable
fun TuitionFeeScreen(navController: NavHostController){
//    //val viewModel: AddTuitionHostelFeesViewModel = viewModel()
//    val gradeSelectedByUser by viewModel.gradeSelected.collectAsState("")
//    val studentsList by viewModel.students.observeAsState(initial = emptyList())
//    val context = LocalContext.current
//
//    val formatter = SimpleDateFormat("dd/MM/yy")
//
//    Scaffold(modifier = Modifier.fillMaxSize(),
//        topBar = { CustomTopBar(text = "Tuition Fee")}
//    )
//    {
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(colorResource(id = R.color.secondary_gray))
//                .padding(10.dp)
//        ) {
//            // horizontal Row -> grades
//            item {
//                Spacer(modifier = Modifier.height(57.dp))
//                LazyRow(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                ) {
//                    items(12) {item ->
//                        CustomHorizontalCard(grade = (item + 1).toString(), onClick = {
//                            viewModel.selectGrade(item+1)
//                            viewModel.loadStudents()
//                            Toast.makeText(context, "Grade ${(item+1)} selected", Toast.LENGTH_SHORT).show()
//                        })
//                        Spacer(modifier = Modifier.width(5.dp))
//                    }
//                }
//                Spacer(modifier = Modifier.height(10.dp))
//
//                // grade selected button
//                GradeSelectedButton(grade = "Grade $gradeSelectedByUser", onClick = {})
//                Spacer(modifier = Modifier.width(7.dp))
//                GradeSelectedButton(grade = "Paid", onClick = {})
//                Spacer(modifier = Modifier.width(7.dp))
//                GradeSelectedButton(grade = "Not Paid", onClick = {})
//                Spacer(modifier = Modifier.height(10.dp))
//            }
//
//            // student list
//            item{
//                studentsList.forEach { student ->
//                    FeesPaidStudentCard(name = student.studentName, roll = student.rollNo, amount = student.amount,
//                        paid = student.feesPaid, date = formatter.format(Date(student.date))
//                    )
//
//                    Spacer(modifier = Modifier.height(8.dp))
//                }
//            }
//        }
//    }
}
