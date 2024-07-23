package com.alsalam.alsalamadminapp.View.DailyTrackingScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alsalam.alsalamadminapp.Model.PaymentTypes
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.View.Components.CustomTopBar
import com.alsalam.alsalamadminapp.ViewModel.DailyCollectionViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun TodayHostelFees(){

    val dailCollectionViewModel: DailyCollectionViewModel = viewModel()
    val todayCollection by dailCollectionViewModel.paymentList.collectAsState(emptyList())

    Scaffold(topBar = { CustomTopBar(text = "Today's Hostel Fees") })
    {
        LaunchedEffect(Unit) {
            launch {
                dailCollectionViewModel.loadDailyCollection()
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.secondary_gray1))
                .padding(10.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(90.dp))
                todayCollection.forEach { collection ->
                    if(collection.paymentTypes == PaymentTypes.HostelFees){
                        TodayCollectionCard(collection.studentName, collection.amount.toString(), collection.grade)
                        Spacer(modifier = Modifier.height(7.dp))
                    }
                }
            }
        }
    }
}