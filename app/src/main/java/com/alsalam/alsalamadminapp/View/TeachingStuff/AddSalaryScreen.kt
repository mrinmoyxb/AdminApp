package com.alsalam.alsalamadminapp.View.TeachingStuff

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alsalam.alsalamadminapp.Model.PaymentTypes
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.View.Components.CustomTopBar
import com.alsalam.alsalamadminapp.View.Components.SaveUploadButton
import com.alsalam.alsalamadminapp.View.FeeScreen.CustomDropDownMenuFeesPaid
import com.alsalam.alsalamadminapp.View.FeeScreen.CustomDropDownMenuMonth
import com.alsalam.alsalamadminapp.View.FeeScreen.FeesPaidStudentCard
import com.alsalam.alsalamadminapp.ViewModel.DailyTrackingViewModel.BalanceViewModel
import com.alsalam.alsalamadminapp.ViewModel.DailyTrackingViewModel.ExpenditureViewModel
import com.alsalam.alsalamadminapp.ViewModel.TeacherSalaryViewModel
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("StateFlowValueCalledInComposition", "UnusedMaterial3ScaffoldPaddingParameter",
    "SimpleDateFormat"
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSalaryScreen(teacherSalaryViewModel: TeacherSalaryViewModel, balanceViewModel: BalanceViewModel) {

    val context = LocalContext.current
    val amount by teacherSalaryViewModel.currentTeacherSalary.collectAsState("")
    val expenditureViewModel: ExpenditureViewModel = viewModel()

    Scaffold(topBar = { CustomTopBar(text = "Pay Salary") })
    {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            item {
                // amount
                OutlinedTextField(
                    value = amount,
                    onValueChange = { teacherSalaryViewModel.currentTeacherSalary.value = it },
                    label = { Text("Enter amoount", fontSize = 15.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = colorResource(id = R.color.secondary_gray1)
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                CustomDropDownMenuMonth(viewModel = teacherSalaryViewModel)

                // save button and DAILY EXPENSE
                Spacer(modifier = Modifier.height(80.dp))
                SaveUploadButton(title = "Mark as paid", onClick = {
                    if (amount == "") {
                        Toast.makeText(context, "Please fill up the details", Toast.LENGTH_SHORT).show()
                    } else {
                          teacherSalaryViewModel.addTeacherSalary()
                          expenditureViewModel.amount.value = amount
                          expenditureViewModel.category.value = "TeacherSalary"
                          expenditureViewModel.addExpense()
                          balanceViewModel.deductMoney(amount.toDouble())
                        Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                    }
                })

                Spacer(modifier = Modifier.height(20.dp))

                }

            }
        }
    }
