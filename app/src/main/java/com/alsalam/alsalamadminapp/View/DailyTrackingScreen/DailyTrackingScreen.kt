package com.alsalam.alsalamadminapp.View.DailyTrackingScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.View.Components.CustomTopBar
import com.alsalam.alsalamadminapp.View.Components.FloatingButton
import com.alsalam.alsalamadminapp.View.Components.GradeCard
import com.alsalam.alsalamadminapp.ViewModel.BalanceViewModel
import com.alsalam.alsalamadminapp.ViewModel.DailyCollectionViewModel
import com.alsalam.alsalamadminapp.ViewModel.ExpenditureViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DailyTrackingScreen(navController: NavHostController, balanceViewModel: BalanceViewModel){

    val dailyCollectionViewModel: DailyCollectionViewModel = viewModel()
    val expenditureViewModel: ExpenditureViewModel = viewModel()

    val totalCollection by dailyCollectionViewModel.totalCollection.collectAsState(0.0)
    val totalExpense by expenditureViewModel.totalExpenditure.collectAsState(0.0)

    val currentBalance by balanceViewModel.currentBalance.collectAsState(0.0)

    Scaffold(topBar = { CustomTopBar(text = "Daily Tracking")},
        floatingActionButton = { FloatingButton(navController = navController, route = "expenditureScreen")})
    {
        LaunchedEffect(Unit){
            dailyCollectionViewModel.loadTotalDailyCollection()
            expenditureViewModel.loadTotalDailyExpense()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.secondary_gray1))
                .padding(10.dp)
        ){
            Spacer(modifier = Modifier.height(85.dp))
            Card(modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(colorResource(id = R.color.secondary_blue))
            )
            {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){

                    // Collection
                    Card(modifier = Modifier
                        .height(120.dp)
                        .width(170.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(Color.White)
                    ){
                        Column(modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally)
                        {
                            Text("Collection", fontSize = 22.sp)
                            Text("₹ $totalCollection", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                        }

                    }

                    // Expenditure
                    Card(modifier = Modifier
                        .height(120.dp)
                        .width(170.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(Color.White)
                    ){
                        Column(modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally)
                        {
                            Text("Expenditure", fontSize = 22.sp)
                            Text("₹ $totalExpense", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                        }

                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                ){
                    Card(modifier = Modifier
                        .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(Color.White)
                    ){
                        Column(modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally)
                        {
                            Text("Current Balance: ₹ $currentBalance", fontSize = 22.sp)
                        }

                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            GradeCard(heading = "", grade = "Admission Fees", onClick = {navController.navigate("todayAdmissionFeeCollected")})
            Spacer(modifier = Modifier.height(5.dp))
            GradeCard(heading = "", grade = "Hostel Fees", onClick = {navController.navigate("todayHostelFeesCollected")})
            Spacer(modifier = Modifier.height(5.dp))
            GradeCard(heading = "", grade = "Tuition Fees", onClick = {navController.navigate("todayTuitionFeeCollected")})
            Spacer(modifier = Modifier.height(5.dp))
            GradeCard(heading = "", grade = "Expenditures", onClick = {navController.navigate("todayExpenditure")})
        }
    }
}


