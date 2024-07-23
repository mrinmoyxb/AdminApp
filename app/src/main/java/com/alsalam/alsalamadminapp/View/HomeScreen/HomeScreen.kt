package com.alsalam.alsalamadminapp.View.HomeScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.View.Components.CustomCard

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController){
    Scaffold() {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = colorResource(id = R.color.secondary_gray))
            .padding(all = 12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            // Heading
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(horizontal = 10.dp)){
                    CustomText(heading = "Welcome", fontSize = 50, fontWeight = FontWeight.Medium)
                    CustomText(heading = "school admin made easy", fontSize = 20,
                        fontWeight = FontWeight.Light, modifier = Modifier.offset(y = (-5).dp))
                }
            }
            Spacer(modifier = Modifier.height(20.dp))


            // All Features
            // Students and Staff
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.SpaceEvenly
            ) {
                CustomCard(image = painterResource(id = R.drawable.student), heading = "Students") {
                    navController.navigate(
                        "allClassesScreen"
                    )
                }
                CustomCard(image = painterResource(id = R.drawable.teacher), heading = "Staff", onClick = {
                    navController.navigate(
                        "allSubjectsScreen"
                    )
                })
            }
            Spacer(modifier = Modifier.height(10.dp))

            // Accounting and Class Routine
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomCard(image = painterResource(id = R.drawable.accounting), heading = "Accounting") {
                    navController.navigate(
                        "feesScreen"
                    )
                }
                CustomCard(image = painterResource(id = R.drawable.routine), heading = "Class Routine") {
                    navController.navigate(
                        "classRoutineScreen"
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            // Result and Holidays
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomCard(image = painterResource(id = R.drawable.result), heading = "Result") {
                    navController.navigate(
                        "resultScreen"
                    )
                }
                CustomCard(image = painterResource(id = R.drawable.holiday), heading = "Holidays") {
                    navController.navigate(
                        "holidayScreen"
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            // Festival and Notice
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomCard(image = painterResource(id = R.drawable.festival), heading = "Festival", onClick = {})
                CustomCard(image = painterResource(id = R.drawable.notice), heading = "Notice") {
                    navController.navigate(
                        "noticeScreen"
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

        }
    }
}



@Composable
fun CustomText(heading: String, fontSize: Int, fontWeight: FontWeight, modifier: Modifier = Modifier){
    Text(heading,
        fontSize = fontSize.sp,
        fontWeight = fontWeight,
        modifier = modifier)
}