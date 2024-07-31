package com.alsalam.alsalamadminapp.View.AddStudentScreen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alsalam.alsalamadminapp.Model.rememberImeState
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.View.Components.UploadCard
import com.alsalam.alsalamadminapp.View.FeeScreen.CustomDropDownMenuGrade
import com.alsalam.alsalamadminapp.ViewModel.AddStudentViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class
)
@Preview
@Composable
fun AddStudentScreen(){

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val imeState = remember { mutableStateOf(false) }
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current

    val addStudentViewModel: AddStudentViewModel = viewModel()
    val studentName by addStudentViewModel.studentName.collectAsState("")
    val studentRollNo by addStudentViewModel.studentRollNo.collectAsState("")
    val studentDateOfBirth by addStudentViewModel.studentDateOfBirth.collectAsState("")
    val className by addStudentViewModel.className.collectAsState("")
    val studentId by addStudentViewModel.studentId.collectAsState("")

    val father by addStudentViewModel.fathersName.collectAsState("")
    val mother by addStudentViewModel.motherName.collectAsState("")
    val village by addStudentViewModel.village.collectAsState("")
    val postOffice by addStudentViewModel.postOffice.collectAsState("")
    val policeStation by addStudentViewModel.policeStation.collectAsState("")
    val district by addStudentViewModel.district.collectAsState("")
    val pin by addStudentViewModel.pin.collectAsState("")
    val mobileNo by addStudentViewModel.mobileNo.collectAsState("")
    val admissionDate by addStudentViewModel.admissionDate.collectAsState("")
    val admissionFees by addStudentViewModel.admissionFees.collectAsState("")
    val monthlyFees by addStudentViewModel.monthlyFees.collectAsState("")

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        addStudentViewModel.handleImageUri(uri)
    }

    val scrollState = rememberScrollState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.secondary_gray))
            .padding(all = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {

            LaunchedEffect(imeState.value) {
                if (imeState.value) {
                    scrollState.animateScrollTo(Int.MAX_VALUE)
                } else {
                    scrollState.animateScrollTo(0)
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
            // add image
            UploadCard(title = "Add Image", onClick = { launcher.launch("image/*") })

            // name:
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = studentName,
                onValueChange = { addStudentViewModel.studentName.value = it },
                label = { Text("Enter the name", fontSize = 15.sp) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = colorResource(id = R.color.secondary_gray1)
                )
            )

            // roll no:
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = studentRollNo,
                onValueChange = { addStudentViewModel.studentRollNo.value = it },
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
            OutlinedTextField(
                value = studentDateOfBirth,
                onValueChange = { addStudentViewModel.studentDateOfBirth.value = it },
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
            OutlinedTextField(
                value = studentId,
                onValueChange = { addStudentViewModel.studentId.value = it },
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
            OutlinedTextField(
                value = father,
                onValueChange = { addStudentViewModel.fathersName.value = it },
                label = { Text("Enter father's name", fontSize = 15.sp) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = colorResource(id = R.color.secondary_gray1)
                )
            )

            // mother name
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = mother,
                onValueChange = { addStudentViewModel.motherName.value = it },
                label = { Text("Enter mother's name", fontSize = 15.sp) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = colorResource(id = R.color.secondary_gray1)
                )
            )

            // village
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = village,
                onValueChange = { addStudentViewModel.village.value = it },
                label = { Text("Enter village", fontSize = 15.sp) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = colorResource(id = R.color.secondary_gray1)
                )
            )

            // post office
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = postOffice,
                onValueChange = { addStudentViewModel.postOffice.value = it },
                label = { Text("Enter Post Office", fontSize = 15.sp) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = colorResource(id = R.color.secondary_gray1)
                )
            )

            // police station
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = policeStation,
                onValueChange = { addStudentViewModel.policeStation.value = it },
                label = { Text("Enter Police Station", fontSize = 15.sp) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = colorResource(id = R.color.secondary_gray1)
                )
            )

            // district
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = district,
                onValueChange = { addStudentViewModel.district.value = it },
                label = { Text("Enter District", fontSize = 15.sp) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = colorResource(id = R.color.secondary_gray1)
                )
            )

            // pin
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = pin,
                onValueChange = { addStudentViewModel.pin.value = it },
                label = { Text("Enter PIN", fontSize = 15.sp) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = colorResource(id = R.color.secondary_gray1)
                )
            )

            // mobile no:
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = mobileNo,
                onValueChange = { addStudentViewModel.mobileNo.value = it },
                label = { Text("Enter mobile number", fontSize = 15.sp) },
                modifier = Modifier.fillMaxWidth()
                    .onFocusEvent {
                        if (it.isFocused) {
                            coroutineScope.launch {
                                delay(200)
                                bringIntoViewRequester.bringIntoView()
                            }
                        }
                    }
                    .bringIntoViewRequester(bringIntoViewRequester),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus()
                        keyboardController?.hide()
                    }
                ),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = colorResource(id = R.color.secondary_gray1)
                )
            )

            // admission date
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = admissionDate,
                onValueChange = { addStudentViewModel.admissionDate.value = it },
                label = { Text("Enter Admission Date", fontSize = 15.sp) },
                modifier = Modifier.fillMaxWidth()
                    .onFocusEvent {
                        if (it.isFocused) {
                            coroutineScope.launch {
                                delay(200)
                                bringIntoViewRequester.bringIntoView()
                            }
                        }
                    }
                    .bringIntoViewRequester(bringIntoViewRequester),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus()
                        keyboardController?.hide()
                    }
                ),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = colorResource(id = R.color.secondary_gray1)
                )
            )

            // admission fees
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = admissionFees,
                onValueChange = { addStudentViewModel.admissionFees.value = it },
                label = { Text("Enter Admission Fees", fontSize = 15.sp) },
                modifier = Modifier.fillMaxWidth().onFocusEvent {
                    if (it.isFocused) {
                        coroutineScope.launch {
                            delay(200)
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                }
                    .bringIntoViewRequester(bringIntoViewRequester),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus()
                        keyboardController?.hide()
                    }
                ),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = colorResource(id = R.color.secondary_gray1)
                )
            )

            // monthly fees
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = monthlyFees,
                onValueChange = { addStudentViewModel.monthlyFees.value = it },
                label = { Text("Enter Monthly Fees", fontSize = 15.sp) },
                modifier = Modifier.fillMaxWidth().onFocusEvent {
                    if (it.isFocused) {
                        coroutineScope.launch {
                            delay(200)
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = colorResource(id = R.color.secondary_gray1)
                )
            )

            // Add Student Button
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.secondary_blue)),
                onClick = {
                    if (studentName.isEmpty() || studentRollNo.isEmpty() || studentDateOfBirth.isEmpty() || className.isEmpty() || studentId.isEmpty()) {
                        Toast.makeText(context, "Enter all the details", Toast.LENGTH_SHORT).show()
                    } else {
                        addStudentViewModel.addStudentByStudentID()
                        addStudentViewModel.addStudentFireStore()
                        addStudentViewModel.addAllStudent()
                        Toast.makeText(context, "Successfully Added", Toast.LENGTH_SHORT).show()
                    }
                })
            {
                Text("Add Student")
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}


