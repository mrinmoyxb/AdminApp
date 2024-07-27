package com.alsalam.alsalamadminapp.View.TeachingStuff

import android.annotation.SuppressLint
import android.widget.VideoView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.View.Components.CustomTopBar
import com.alsalam.alsalamadminapp.View.Components.TeacherDisplayCard
import com.alsalam.alsalamadminapp.ViewModel.AddStaffViewModel
import com.alsalam.alsalamadminapp.ViewModel.TeacherSalaryViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun SubjectWiseTeacher(navController: NavHostController,viewModel: AddStaffViewModel, teacherSalaryViewModel: TeacherSalaryViewModel) {

    val teachers by viewModel.teacher.collectAsState(emptyList())

    Scaffold(topBar = { CustomTopBar(text = "${viewModel.subject.value} Teachers") })
    {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.secondary_gray1))
                .padding(10.dp)
        )
        {
            item {
                Spacer(modifier = Modifier.height(85.dp))
                teachers.forEach { teacher ->
                    TeacherDisplayCard(name = teacher.teacherName, qualification = teacher.qualification, address = teacher.addressOfTeacher,
                        dateOfAppointment = teacher.dateOfAppointment, amount = teacher.salary.toString(),
                        onClick = {
                            teacherSalaryViewModel.currentTeacherName.value = teacher.teacherName
                            teacherSalaryViewModel.currentTeacherSubject.value = teacher.subject
                            teacherSalaryViewModel.currentTeacherQualification.value = teacher.qualification
                            navController.navigate("teacherDetailSalary")
                        })
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

    }
}