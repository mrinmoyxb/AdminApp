package com.alsalam.alsalamadminapp.View.TeachingStuff

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alsalam.alsalamadminapp.Model.Subjects
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.View.Components.CustomTopBar
import com.alsalam.alsalamadminapp.View.Components.SaveUploadButton
import com.alsalam.alsalamadminapp.View.FeeScreen.CustomDropDownMenuDesignation
import com.alsalam.alsalamadminapp.View.FeeScreen.CustomDropDownMenuTeacherSubject
import com.alsalam.alsalamadminapp.ViewModel.AddStaffViewModel
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun AddTeachingStaff(){
    val context = LocalContext.current
    val teacherViewModel: AddStaffViewModel = viewModel()

    val teacherName by teacherViewModel.teacherName.collectAsState("")
    val teacherSalary by teacherViewModel.salary.collectAsState("")
    val address by teacherViewModel.addressOfTeacher.collectAsState("")
    val dateOfAppointment by teacherViewModel.dateOfAppointment.collectAsState("")
    val qualification by teacherViewModel.qualification.collectAsState("")
    val bioData by teacherViewModel.bioData.collectAsState("")

    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val bringIntoViewRequester = BringIntoViewRequester()

    Scaffold(topBar = { CustomTopBar(text = "Add Staff")})
    {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.secondary_gray))
                .padding(all = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            item {
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
                CustomDropDownMenuTeacherSubject(viewModel = teacherViewModel)

                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth())
                {
                    Text("Select Designation")
                }
                Spacer(modifier = Modifier.height(10.dp))
                CustomDropDownMenuDesignation(viewModel = teacherViewModel)
                Spacer(modifier = Modifier.height(10.dp))

                // date of appointment:
                OutlinedTextField(
                    value = dateOfAppointment,
                    onValueChange = { teacherViewModel.dateOfAppointment.value = it.toString() },
                    label = { Text("Enter date of appointment", fontSize = 15.sp) },
                    placeholder = { Text("DD/MM/YYYY", fontSize = 15.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = colorResource(id = R.color.secondary_gray1)
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))

                // qualification:
                OutlinedTextField(
                    value = qualification,
                    onValueChange = { teacherViewModel.qualification.value = it.toString() },
                    label = { Text("Enter qualification", fontSize = 15.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = colorResource(id = R.color.secondary_gray1)
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))

                // address:
                OutlinedTextField(
                    value = address,
                    onValueChange = { teacherViewModel.addressOfTeacher.value = it.toString() },
                    label = { Text("Enter address", fontSize = 15.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = colorResource(id = R.color.secondary_gray1)
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))

                // bio data:
                OutlinedTextField(
                    value = bioData,
                    onValueChange = { teacherViewModel.bioData.value = it.toString() },
                    label = { Text("Enter bio data", fontSize = 15.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
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
                    modifier = Modifier.fillMaxWidth()
                        .onFocusEvent { event->
                            if(event.isFocused){
                                coroutineScope.launch {
                                    bringIntoViewRequester.bringIntoView()
                                }
                            }
                        },
                    shape = RoundedCornerShape(10.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()}),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = colorResource(id = R.color.secondary_gray1)
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))

                // button
                SaveUploadButton(title = "Add Teacher") {
                    teacherViewModel.addTeacherBySubject()
                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
                }

                Spacer(modifier = Modifier.height(20.dp))

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