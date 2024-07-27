package com.alsalam.alsalamadminapp.View.TeachingStuff

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alsalam.alsalamadminapp.Model.Subjects
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.View.Components.CustomTopBar
import com.alsalam.alsalamadminapp.View.Components.SaveUploadButton
import com.alsalam.alsalamadminapp.ViewModel.AddStaffViewModel

@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AddTeachingStaff(){
    val context = LocalContext.current
    val teacherViewModel: AddStaffViewModel = viewModel()

    val teacherName by teacherViewModel.teacherName.collectAsState("")
    val teacherSalary by teacherViewModel.salary.collectAsState("")
    val subjects by teacherViewModel.subject.collectAsState("")
    val dateOfAppointment by teacherViewModel.dateOfAppointemnt.collectAsState("")
    val qualification by teacherViewModel.qualification.collectAsState("")
    val bioData by teacherViewModel.bioData.collectAsState("")

    Scaffold(topBar = { CustomTopBar(text = "Add Staff")})
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.secondary_gray))
                .padding(all = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            // name:
            Spacer(modifier = Modifier.height(90.dp))
            OutlinedTextField(
                value = teacherName,
                onValueChange = { teacherViewModel.teacherName.value = it },
                label = { Text("Enter name", fontSize = 15.sp) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = colorResource(id = R.color.secondary_gray1)
                )
            )

            // subjects
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth())
            {
                Text("Select subject")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround)
            {
                CustomSelectButton(width = 100, subject = Subjects.English.toString(), onClick = {
                    teacherViewModel.subject.value = Subjects.English
                    Toast.makeText(context, "${teacherViewModel.subject.value} selected", Toast.LENGTH_SHORT).show()
                })
                CustomSelectButton(width = 100, subject = Subjects.Hindi.toString(), onClick = {
                    teacherViewModel.subject.value = Subjects.Hindi
                    Toast.makeText(context, "${teacherViewModel.subject.value} selected", Toast.LENGTH_SHORT).show()
                })
                CustomSelectButton(width = 120, subject = Subjects.Mathematics.toString(), onClick = {
                    teacherViewModel.subject.value = Subjects.Mathematics
                    Toast.makeText(context, "${teacherViewModel.subject.value} selected", Toast.LENGTH_SHORT).show()
                })
            }

            Spacer(modifier = Modifier.height(15.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround)
            {
                CustomSelectButton(width = 150, subject = Subjects.PhysicalEducation.toString(), onClick = {
                    teacherViewModel.subject.value = Subjects.PhysicalEducation
                    Toast.makeText(context, "${teacherViewModel.subject.value} selected", Toast.LENGTH_SHORT).show()
                })
                CustomSelectButton(width = 120, subject = Subjects.SocialScience.toString(), onClick = {
                    teacherViewModel.subject.value = Subjects.SocialScience
                    Toast.makeText(context, "${teacherViewModel.subject.value} selected", Toast.LENGTH_SHORT).show()
                })
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly)
            {
                CustomSelectButton(width = 100, subject = Subjects.Science.toString(), onClick = {
                    teacherViewModel.subject.value = Subjects.Science
                    Toast.makeText(context, "${teacherViewModel.subject.value} selected", Toast.LENGTH_SHORT).show()
                })
            }
            Spacer(modifier = Modifier.height(30.dp))

            // date of appointment:
            OutlinedTextField(
                value = teacherSalary,
                onValueChange = { teacherViewModel.dateOfAppointemnt.value = it.toString() },
                label = { Text("Enter date of appointment", fontSize = 15.sp) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = colorResource(id = R.color.secondary_gray1)
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            // qualification:
            OutlinedTextField(
                value = teacherSalary,
                onValueChange = { teacherViewModel.qualification.value = it.toString() },
                label = { Text("Enter qualification", fontSize = 15.sp) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = colorResource(id = R.color.secondary_gray1)
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            // bio data:
            OutlinedTextField(
                value = teacherSalary,
                onValueChange = { teacherViewModel.bioData.value = it.toString() },
                label = { Text("Enter bio data", fontSize = 15.sp) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = colorResource(id = R.color.secondary_gray1)
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            // salary:
            OutlinedTextField(
                value = teacherSalary,
                onValueChange = { teacherViewModel.salary.value = it.toString() },
                label = { Text("Enter salary", fontSize = 15.sp) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = colorResource(id = R.color.secondary_gray1)
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            // button
            SaveUploadButton(title = "Add Staff") {
                teacherViewModel.addTeacher()
                Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun CustomSelectButton(width: Int, subject: String, onClick: ()-> Unit){
    Card(modifier = Modifier
        .width(width.dp)
        .height(40.dp)
        .clickable {
            onClick()
        },
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(colorResource(id = R.color.secondary_blue)),
        elevation = CardDefaults.cardElevation(10.dp))
    {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Text(subject, fontWeight = FontWeight.Normal, fontSize = 15.sp, color = Color.White)
        }
    }
}