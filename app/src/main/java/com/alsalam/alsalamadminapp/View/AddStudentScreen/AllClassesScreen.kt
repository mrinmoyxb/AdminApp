package com.alsalam.alsalamadminapp.View.AddStudentScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.View.Components.FloatingButton
import com.alsalam.alsalamadminapp.View.Components.GradeCard
import com.alsalam.alsalamadminapp.ViewModel.AddStudentViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun AllClassesScreen(navController: NavHostController, viewModel: AddStudentViewModel){

    Scaffold(
        floatingActionButton = { FloatingButton(navController, route = "addStudent")}
    ) {
        val grades: List<String> = listOf("Ankur", "PP", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI(Arts)", "XI(Science)", "XII(Arts)", "XII(Science)")
        Box(modifier = Modifier.fillMaxWidth().height(45.dp)){}
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.secondary_gray))
            .padding(horizontal = 10.dp, vertical = 40.dp))
        {
            item {
                grades.forEach { grade ->
                    Spacer(modifier = Modifier.height(8.dp))
                    GradeCard(grade = grade) {
                        viewModel.onGradeSelected(grade)
                        navController.navigate("displayStudentGradeWise")
                        viewModel.loadStudents()
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}