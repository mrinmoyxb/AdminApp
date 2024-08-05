package com.alsalam.alsalamadminapp.View.FeeScreen

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alsalam.alsalamadminapp.Model.PaymentTypes
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.View.Components.CustomTopBar
import com.alsalam.alsalamadminapp.View.Components.GradeCard
import com.alsalam.alsalamadminapp.View.Components.SaveUploadButton
import com.alsalam.alsalamadminapp.ViewModel.DailyTrackingViewModel.BalanceViewModel
import com.alsalam.alsalamadminapp.ViewModel.DailyTrackingViewModel.DailyCollectionViewModel
import com.alsalam.alsalamadminapp.ViewModel.MonthlyPaymentViewModel
import com.alsalam.alsalamadminapp.ViewModel.PaymentViewModel
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("StateFlowValueCalledInComposition", "SimpleDateFormat",
    "UnusedMaterial3ScaffoldPaddingParameter"
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTuitionFeeScreen(paymentViewModel: PaymentViewModel, dailyExpenseViewModel: DailyCollectionViewModel, balanceViewModel: BalanceViewModel, monthlyPaymentViewModel: MonthlyPaymentViewModel) {
    val amount by paymentViewModel.currentPaymentAmount.collectAsState("")
    val serviceSelected by paymentViewModel.serviceSelected.collectAsState(-1)
    val context = LocalContext.current
    val students by paymentViewModel.studentsPaymentFetched.collectAsState(emptyList())
    val dateSet by paymentViewModel.dateSetByAdmin.collectAsState("")

    val formatter = SimpleDateFormat("dd/MM/yyyy")

    LaunchedEffect(Unit) {
        paymentViewModel.fetchStudentFees()
    }

    Scaffold(topBar = { CustomTopBar(text = "Tuition Fees") })
    {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp))
        {
            item {
                Spacer(modifier = Modifier.height(95.dp))
                // amount
                OutlinedTextField(
                    value = amount,
                    onValueChange = { paymentViewModel.currentPaymentAmount.value = it },
                    label = { Text("Enter Amount", fontSize = 15.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = colorResource(id = R.color.secondary_gray1)
                    )
                )

                // date
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = dateSet,
                    onValueChange = { paymentViewModel.dateSetByAdmin.value = it },
                    label = { Text("Enter Date", fontSize = 15.sp) },
                    placeholder = { Text("DD/MM/YYYY", fontSize = 15.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = colorResource(id = R.color.secondary_gray1)
                    )
                )

                // paid : true or false
                Spacer(modifier = Modifier.height(10.dp))
                Text("Paid", fontSize = 15.sp, fontWeight = FontWeight.Normal)
                Spacer(modifier = Modifier.height(5.dp))
                CustomDropDownMenuFeesPaid(viewModel = paymentViewModel)
                Spacer(modifier = Modifier.height(10.dp))

                // save button
                SaveUploadButton(title = "Save", onClick = {
                    if (amount == "") {
                        Toast.makeText(context, "Please fill up the details", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        paymentViewModel.addTuitionFees()
                        dailyExpenseViewModel.amount.value = amount.toDouble()
                        dailyExpenseViewModel.paymentTypes.value = PaymentTypes.TuitionFees
                        dailyExpenseViewModel.storePayment()
                        balanceViewModel.addMoney(amount.toDouble())

                        monthlyPaymentViewModel.currentPaymentAmount.value = amount
                        monthlyPaymentViewModel.currentIsFeePaid.value = paymentViewModel.currentIsFeePaid.value
                        monthlyPaymentViewModel.addMonthlyTuitionPayment()
                        Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                    }
                })

                Spacer(modifier = Modifier.height(10.dp))
                Subheading(grade = "Previous Payments")

                Spacer(modifier = Modifier.height(20.dp))
                students.forEach { student ->
                    if (student.studentPaymentFor == PaymentTypes.TuitionFees) {
                        FeesPaidStudentCard(
                            name = student.studentName,
                            amount = student.studentPaymentAmount.toString(),
                            paid = student.studentFeesPaid,
                            date = student.dateSetByAdmin
                        )
                        Spacer(modifier = Modifier.height(7.dp))
                    }
                }

            }
        }
    }
}

