package com.alsalam.alsalamadminapp.View.FeeScreen.MonthlyFeeScreen

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
import com.alsalam.alsalamadminapp.ViewModel.MonthlyPaymentViewModel
import com.alsalam.alsalamadminapp.ViewModel.PaymentViewModel
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdmissionFeeDisplayScreen(navController: NavHostController) {

    val viewModel: MonthlyPaymentViewModel = viewModel()
    val gradeSelectedByAdmin by viewModel.fetchGradeSelected.collectAsState(0)
    val output by viewModel.students.collectAsState(emptyList())

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { CustomTopBar(text = "Admission Fee") })
    {
        val grades: List<String> = listOf("Ankur", "PP", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI(Arts)", "XI(Science)", "XII(Arts)", "XII(Science)")

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
                    item {
                        grades.forEach { grade ->
                            CustomHorizontalCard(
                                grade = grade,
                                onClick = {
                                    viewModel.fetchGradeSelected.value = grade
                                    viewModel.loadMonthlyAdmissionPayment()
                                })
                        }
                    }
                }

                // grade selected button
                Spacer(modifier = Modifier.height(15.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    GradeSelectedButton(grade = "Grade $gradeSelectedByAdmin", onClick = {})
                    Spacer(modifier = Modifier.width(7.dp))
                }
                Spacer(modifier = Modifier.height(10.dp))


                // student list
                output.forEach { student ->
                    if (student.studentGrade == gradeSelectedByAdmin) {
                        StudentDisplayCard(
                            name = student.studentName,
                            studentId = "",
                            roll = student.studentRollNo,
                            dob = "",
                            onClick = {})
                    }
                    Spacer(modifier = Modifier.height(5.dp))

                }
            }
        }
    }
}