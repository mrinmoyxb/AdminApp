package com.alsalam.alsalamadminapp.View.TeachingStuff

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.alsalam.alsalamadminapp.Model.Subjects
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.View.Components.FloatingButton
import com.alsalam.alsalamadminapp.View.Components.GradeCard
import com.alsalam.alsalamadminapp.ViewModel.AddStaffViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AllSubjectsScreen(navController: NavHostController, viewModel: AddStaffViewModel){
    val listOfSubjects = listOf(Subjects.English, Subjects.Hindi, Subjects.Mathematics, Subjects.Science, Subjects.SocialScience, Subjects.PhysicalEducation)

    Scaffold(
        floatingActionButton = {
            FloatingButton(navController = navController, route = "addTeachingStaff")
        },
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)){}
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.secondary_gray1))
            .padding(horizontal = 10.dp, vertical = 40.dp))
        {
            item {
                listOfSubjects.forEach { subject ->
                    Spacer(modifier = Modifier.height(8.dp))
                    GradeCard(heading = "", grade = subject.toString(), onClick = {
                        viewModel.subject.value = subject
                        viewModel.loadTeachers()
                        navController.navigate("subjectWiseTeacher")
                    })
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
        }

    }
}