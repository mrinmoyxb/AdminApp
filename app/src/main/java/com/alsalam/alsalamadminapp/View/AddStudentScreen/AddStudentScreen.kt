package com.alsalam.alsalamadminapp.View.AddStudentScreen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.View.Components.UploadCard
import com.alsalam.alsalamadminapp.View.FeeScreen.CustomDropDownMenuGrade
import com.alsalam.alsalamadminapp.ViewModel.AddStudentViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStudentScreen(){

    val context = LocalContext.current

    val addStudentViewModel: AddStudentViewModel = viewModel()
    val studentName by addStudentViewModel.studentName.collectAsState("")
    val studentRollNo by addStudentViewModel.studentRollNo.collectAsState("")
    val studentDateOfBirth by addStudentViewModel.studentDateOfBirth.collectAsState("")
    val className by addStudentViewModel.className.collectAsState("")
    val studentId by addStudentViewModel.studentId.collectAsState("")

    //val fatherName by addStudentViewModel.fatherName.collectAsState("")

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        addStudentViewModel.handleImageUri(uri)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.secondary_gray))
            .padding(all = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        // add image
        UploadCard(title = "Add Image", onClick = {launcher.launch("image/*")})

        // name:
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = studentName,
            onValueChange = {addStudentViewModel.studentName.value = it},
            label = { Text("Enter the name", fontSize = 15.sp) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.secondary_gray1)
            )
        )

        // roll no:
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = studentRollNo,
            onValueChange = {addStudentViewModel.studentRollNo.value = it},
            label = { Text("Enter the roll no", fontSize = 15.sp) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.secondary_gray1)
            )
        )

        // date of birth:
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = studentDateOfBirth,
            onValueChange = {addStudentViewModel.studentDateOfBirth.value = it},
            label = { Text("Enter the date of birth", fontSize = 15.sp) },
            placeholder = { Text("DD/MM/YYYY") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.secondary_gray1)
            )
        )

        // grade:
        Spacer(modifier = Modifier.height(8.dp))
        CustomDropDownMenuGrade(viewModel = addStudentViewModel)

        // student id
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = studentId,
            onValueChange = {addStudentViewModel.studentId.value= it},
            label = { Text("Enter student id", fontSize = 15.sp) },
            placeholder = { Text("name_rollno_grade") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.secondary_gray1)
            )
        )

        // ADDITIONAL
        // father name
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = studentId,
            onValueChange = {addStudentViewModel.studentId.value= it},
            label = { Text("Enter father's name", fontSize = 15.sp) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.secondary_gray1)
            )
        )

        // mother name
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = studentId,
            onValueChange = {addStudentViewModel.studentId.value= it},
            label = { Text("Enter mother's name", fontSize = 15.sp) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.secondary_gray1)
            )
        )

        // village
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = studentId,
            onValueChange = {addStudentViewModel.studentId.value= it},
            label = { Text("Enter village", fontSize = 15.sp) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.secondary_gray1)
            )
        )

        // post office
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = studentId,
            onValueChange = {addStudentViewModel.studentId.value= it},
            label = { Text("Enter Post Office", fontSize = 15.sp) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.secondary_gray1)
            )
        )

        // police station
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = studentId,
            onValueChange = {addStudentViewModel.studentId.value= it},
            label = { Text("Enter Police Station", fontSize = 15.sp) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.secondary_gray1)
            )
        )

        // district
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = studentId,
            onValueChange = {addStudentViewModel.studentId.value= it},
            label = { Text("Enter District", fontSize = 15.sp) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.secondary_gray1)
            )
        )

        // pin
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = studentId,
            onValueChange = {addStudentViewModel.studentId.value= it},
            label = { Text("Enter PIN", fontSize = 15.sp) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.secondary_gray1)
            )
        )

        // mobile no:
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = studentId,
            onValueChange = {addStudentViewModel.studentId.value= it},
            label = { Text("Enter mobile number", fontSize = 15.sp) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.secondary_gray1)
            )
        )

        // admission date
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = studentId,
            onValueChange = {addStudentViewModel.studentId.value= it},
            label = { Text("Enter Admission Date", fontSize = 15.sp) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.secondary_gray1)
            )
        )

        // admission fees
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = studentId,
            onValueChange = {addStudentViewModel.studentId.value= it},
            label = { Text("Enter Admission Fees", fontSize = 15.sp) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.secondary_gray1)
            )
        )

        // monthly fees
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = studentId,
            onValueChange = {addStudentViewModel.studentId.value= it},
            label = { Text("Enter Monthly Fees", fontSize = 15.sp) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.secondary_gray1)
            )
        )

        // Add Student Button
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.secondary_blue)),
            onClick = {
                if(studentName.isEmpty() || studentRollNo.isEmpty() || studentDateOfBirth.isEmpty() || className.isEmpty() || studentId.isEmpty()){
                    Toast.makeText(context, "Enter all the details", Toast.LENGTH_SHORT).show()
                }
                else{
                    addStudentViewModel.addStudentByStudentID()
                    addStudentViewModel.addStudentFireStore()
                    addStudentViewModel.addAllStudent()
                    Toast.makeText(context, "Successfully Added", Toast.LENGTH_SHORT).show()
                }
            })
        {
            Text("Add Student")
        }
    }
}

@Preview
@Composable
fun Display10(){
    AddStudentScreen()
}