package com.alsalam.alsalamadminapp.View.FeeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.View.Components.SelectCard

@Composable
fun FeeScreen(navController: NavHostController){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.secondary_gray))
            .padding(10.dp)
    ){
        SelectCard(heading = "Daily Tracking", onClick = {navController.navigate("dailyTrackingScreen")})
        Spacer(modifier = Modifier.height(10.dp))

        SelectCard(heading = "Admission Fees", onClick = {})
        Spacer(modifier = Modifier.height(10.dp))

        SelectCard(heading = "Tuition Fees", onClick = {})
        Spacer(modifier = Modifier.height(10.dp))

        SelectCard(heading = "Hostel Fees", onClick = {navController.navigate("hostelFees")})
        Spacer(modifier = Modifier.height(10.dp))
    }

}