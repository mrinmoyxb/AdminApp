package com.alsalam.alsalamadminapp.View.DailyTrackingScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alsalam.alsalamadminapp.Model.PaymentTypes
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.View.Components.CustomTopBar
import com.alsalam.alsalamadminapp.View.Components.GradeCard
import com.alsalam.alsalamadminapp.View.Components.SaveUploadButton
import com.alsalam.alsalamadminapp.View.FeeScreen.GradeSelectedButton
import com.alsalam.alsalamadminapp.ViewModel.PreviousPayments
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun PreviousPaymentScreen(){

    val viewModel: PreviousPayments = viewModel()
    val dateSetByAdmin by viewModel.dateByAdmin.collectAsState("")
    val previousPaymentsCollection by viewModel.paymentListCollection.collectAsState()
    val previousExpense by viewModel.paymentListExpense.collectAsState()

    val totalCollection by viewModel.totalCollection.collectAsState()
    val totalExpense by viewModel.totalExpense.collectAsState()

    Scaffold(topBar = { CustomTopBar(text = "Previous Payments") })
    {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        )
        {
            item {
                Spacer(modifier = Modifier.height(95.dp))
                // amount
                OutlinedTextField(
                    value = dateSetByAdmin,
                    onValueChange = { viewModel.dateByAdmin.value = it },
                    label = { Text("Enter a Date", fontSize = 15.sp) },
                    placeholder = {Text("DD/MM/YYYY")},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = colorResource(id = R.color.secondary_gray1)
                    )
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                SaveUploadButton(title = "Search") {
                    viewModel.loadCollection()
                    viewModel.loadExpenditure()
                }


                Spacer(modifier = Modifier.height(12.dp))

                GradeSelectedButton(grade = "Payments", width = 100) {}
                Spacer(modifier = Modifier.height(10.dp))

                GradeCard(
                    heading = "",
                    grade = "Total Collection: ₹ ${viewModel.totalCollection.value}",
                    fontSize = 20,
                    onClick = { })
                Spacer(modifier = Modifier.height(5.dp))
                GradeCard(
                    heading = "",
                    grade = "Total Expense: ₹ ${viewModel.totalExpense.value}",
                    fontSize = 20,
                    onClick = {  })

                Spacer(modifier = Modifier.height(10.dp))
                GradeSelectedButton(grade = "Collections", width = 100) {}
                Spacer(modifier = Modifier.height(10.dp))

                if(previousPaymentsCollection.isEmpty()){
                    Row(modifier =Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                        Text("Not Available ")
                    }
                }
                else{
                    previousPaymentsCollection.forEach { payment ->
                        PaymentFetchedCard(name = payment.studentName, amount = payment.amount.toString(),
                            grade = payment.grade.toString(), type = payment.paymentTypes)
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                GradeSelectedButton(grade = "Expenses", width = 100) {}
                Spacer(modifier = Modifier.height(10.dp))

                Spacer(modifier = Modifier.height(15.dp))
                if(previousExpense.isEmpty()){
                    Row(modifier =Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                        Text("Not Available ")
                    }
                }
                else{
                    previousExpense.forEach { payment ->
                        TodayExpenseCard(name = payment.category, amount = payment.amount.toString())
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
            }

        }
    }
}



@Composable
fun PaymentFetchedCard(name: String, amount: String, grade: String, type: PaymentTypes){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(140.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(colorResource(id = R.color.secondary_blue))
    )
    {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp))
        {
            Spacer(modifier = Modifier.height(5.dp))
            Text(name.uppercase(), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 22.sp)
            Spacer(modifier = Modifier.height(5.dp))
            Text("Class: $grade", color = Color.White, fontWeight = FontWeight.Medium, fontSize = 18.sp)
            Text("Amount: ₹ $amount", color = Color.White, fontWeight = FontWeight.Medium, fontSize = 18.sp)
            Text("$type", color = Color.White, fontWeight = FontWeight.Medium, fontSize = 18.sp)
        }

    }
}



