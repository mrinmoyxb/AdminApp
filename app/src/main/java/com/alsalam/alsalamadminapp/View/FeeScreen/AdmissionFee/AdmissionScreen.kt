package com.alsalam.alsalamadminapp.View.FeeScreen.AdmissionFee

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alsalam.alsalamadminapp.Model.PaymentTypes
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.View.Components.CustomTopBar
import com.alsalam.alsalamadminapp.View.Components.SaveUploadButton
import com.alsalam.alsalamadminapp.ViewModel.AdmissionFeeViewModel
import com.alsalam.alsalamadminapp.ViewModel.DailyTrackingViewModel.BalanceViewModel
import com.alsalam.alsalamadminapp.ViewModel.DailyTrackingViewModel.DailyCollectionViewModel

@SuppressLint("StateFlowValueCalledInComposition", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdmissionFeeScreen(viewModel: AdmissionFeeViewModel, dailyExpenseViewModel: DailyCollectionViewModel, balanceViewModel: BalanceViewModel){

    val admissionFee by viewModel.admissionFee.collectAsState("")
    val examFee by viewModel.examFee.collectAsState("")
    val sportsFee by viewModel.sportsFee.collectAsState("")
    val medicalFee by viewModel.medicalFee.collectAsState("")
    val estFee by viewModel.estFee.collectAsState("")
    val transportFee by viewModel.transportFee.collectAsState("")
    val occasionFee by viewModel.occasionFee.collectAsState("")
    val bookFee by viewModel.bookFee.collectAsState("")
    val miscellaneousFee by viewModel.miscellaneousFee.collectAsState("")
    val lateFee by viewModel.lateFee.collectAsState("")
    val electricityFee by viewModel.electricityFee.collectAsState("")
    val othersFee by viewModel.othersFee.collectAsState("")

    val total by viewModel.totalFee.collectAsState(0.0)
    val context = LocalContext.current

    Scaffold(
        topBar = { CustomTopBar(text = "Admission Fee")}
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.secondary_gray1))
                .padding(all = 10.dp)
        ){
            item{
                Spacer(modifier = Modifier.height(85.dp))

                CustomTextField(text = "Admission Fee", admissionFee, onValueChange = {viewModel.admissionFee.value = it})
                Spacer(modifier = Modifier.height(5.dp))

                CustomTextField(text = "Exam Fee", examFee.toString(), onValueChange = {viewModel.examFee.value = it})
                Spacer(modifier = Modifier.height(5.dp))

                CustomTextField(text = "Sports Fee", sportsFee.toString(), onValueChange = {viewModel.sportsFee.value = it})
                Spacer(modifier = Modifier.height(5.dp))

                CustomTextField(text = "Medical Fee", medicalFee.toString(), onValueChange = {viewModel.medicalFee.value = it})
                Spacer(modifier = Modifier.height(5.dp))

                CustomTextField(text = "Est. Fee", estFee.toString(), onValueChange = {viewModel.estFee.value = it})
                Spacer(modifier = Modifier.height(5.dp))

                CustomTextField(text = "Transport Fee", transportFee.toString(), onValueChange = {viewModel.transportFee.value = it})
                Spacer(modifier = Modifier.height(5.dp))

                CustomTextField(text = "Occasion Fee", occasionFee.toString(), onValueChange = {viewModel.occasionFee.value = it})
                Spacer(modifier = Modifier.height(5.dp))

                CustomTextField(text = "Book Fee", bookFee.toString(), onValueChange = {viewModel.bookFee.value = it})
                Spacer(modifier = Modifier.height(5.dp))

                CustomTextField(text = "Miscellaneous Fee", miscellaneousFee.toString(), onValueChange = {viewModel.miscellaneousFee.value = it})
                Spacer(modifier = Modifier.height(5.dp))

                CustomTextField(text = "Late Fee", lateFee.toString(), onValueChange = {viewModel.lateFee.value = it})
                Spacer(modifier = Modifier.height(5.dp))

                CustomTextField(text = "Electricity Fee", electricityFee.toString(), onValueChange = {viewModel.electricityFee.value = it})
                Spacer(modifier = Modifier.height(5.dp))

                CustomTextField(text = "Others", othersFee.toString(), onValueChange = {viewModel.othersFee.value = it})
                Spacer(modifier = Modifier.height(5.dp))


                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End)
                {
                    Box(modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(colorResource(id = R.color.secondary_blue))
                        .clickable {
                            viewModel.totalBill()
                        },
                        contentAlignment = Alignment.Center)
                    {
                        Text(" Generate Total Bill:  ₹$total", fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))
                SaveUploadButton(title = "Save and Mark as Paid", onClick = {
                    if(total<=0.0){
                        Toast.makeText(context, "Please enter amount!", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        viewModel.addAdmissionFees()
                        dailyExpenseViewModel.amount.value = total
                        dailyExpenseViewModel.paymentTypes.value = PaymentTypes.AdmissionFees
                        dailyExpenseViewModel.storePayment()
                        balanceViewModel.addMoney(total)
                        Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
                    }
                })

                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
    
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(text: String, value: String, onValueChange: (String) -> Unit){

    OutlinedTextField(value = value, onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = {Text(text)},
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = colorResource(id = R.color.secondary_gray1)
        )
    )
}