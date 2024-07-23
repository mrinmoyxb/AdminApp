package com.alsalam.alsalamadminapp.View.DailyTrackingScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.View.Components.CustomTopBar
import com.alsalam.alsalamadminapp.ViewModel.ExpenditureViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TodayExpenditure(){

    val expenditureViewModel: ExpenditureViewModel = viewModel()
    val todayCollection by expenditureViewModel.expenseList.collectAsState(emptyList())

    Scaffold(topBar = { CustomTopBar(text = "Today's Expenditures") })
    {
        LaunchedEffect(Unit) {
            launch {
                expenditureViewModel.loadDailyExpense()
            }
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)){}
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.secondary_gray1))
                .padding(10.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(90.dp))
                todayCollection.forEach { collection ->
                    TodayExpenseCard(name = collection.category, amount = collection.amount.toString())
                    Spacer(modifier = Modifier.height(7.dp))
                }
            }
        }
    }
}

@Composable
fun TodayExpenseCard(name: String, amount: String){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(120.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(colorResource(id = R.color.secondary_blue))
    )
    {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp)){
            Spacer(modifier = Modifier.height(50.dp))
            Text(name.uppercase(), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 25.sp)
            Text("Amount: ₹ $amount", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 22.sp)

        }

    }
}