package com.alsalam.alsalamadminapp.View.AddStudentScreen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.View.Components.CustomCard
import com.alsalam.alsalamadminapp.View.Components.SaveUploadButton
import com.alsalam.alsalamadminapp.View.FeeScreen.GradeSelectedButton
import com.alsalam.alsalamadminapp.ViewModel.PaymentViewModel
import com.alsalam.alsalamadminapp.ViewModel.PdfUploadAndRetrieveViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun StudentDisplayDetailScreen(paymentViewModel: PaymentViewModel, navHostController: NavHostController, uploadResultViewModel: PdfUploadAndRetrieveViewModel){

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uploadResultViewModel.selectPdf(uri)
    }
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.secondary_gray1))
            .padding(10.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(45.dp))

            // student card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                colors = CardDefaults.cardColors(colorResource(id = R.color.secondary_blue))
            )
            {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)
                ) {
                    Column(modifier = Modifier.width(250.dp)) {
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(paymentViewModel.currentActiveName.value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 25.sp)

                        Spacer(modifier = Modifier.height(12.dp))
                        Text("Student ID: ${paymentViewModel.currentStudentId.value}", color = Color.White, fontWeight = FontWeight.Normal, fontSize = 20.sp)
                        Text("Roll.No: ${paymentViewModel.currentActiveRollNo.value}", color = Color.White, fontWeight = FontWeight.Normal, fontSize = 20.sp)
                        Text("DOB: ${paymentViewModel.currentActiveDob.value}", color = Color.White, fontWeight = FontWeight.Normal, fontSize = 20.sp)
                    }
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .height(85.dp)
                                .width(85.dp)
                                .clip(CircleShape)
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Pic")
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))

            GradeSelectedButton(grade = "Fees", onClick = {})
            Spacer(modifier = Modifier.height(10.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Admission Fee
                CustomCard(
                    image = painterResource(id = R.drawable.admission),
                    heading = "Admission Fee",
                    height = 120,
                    width = 120,
                    imageHeight = 33,
                    imageWidth = 33,
                    textHeight = 13,
                    onClick = { navHostController.navigate("admissionFeeScreen") })

                // Tuition Fee
                CustomCard(
                    image = painterResource(id = R.drawable.tuition),
                    heading = "Tuition Fee",
                    height = 120,
                    width = 120,
                    imageHeight = 33,
                    imageWidth = 33,
                    textHeight = 13,
                    onClick = { navHostController.navigate("addTuitionFees") })

                // Hostel Fee
                CustomCard(
                    image = painterResource(id = R.drawable.hostel),
                    heading = "Hostel Fee",
                    height = 120,
                    width = 120,
                    imageHeight = 33,
                    imageWidth = 33,
                    textHeight = 13,
                    onClick = { navHostController.navigate("addHostelFees") })
            }
            Spacer(modifier = Modifier.height(10.dp))


            Row(modifier = Modifier.fillMaxWidth()) {
                // Other Fee
                CustomCard(
                    image = painterResource(id = R.drawable.other),
                    heading = "Other Fee",
                    height = 120,
                    width = 120,
                    imageHeight = 33,
                    imageWidth = 33,
                    textHeight = 13,
                    onClick = { navHostController.navigate("addOtherFees") })

                Spacer(modifier = Modifier.width(15.dp))
                // Late Fee
                CustomCard(
                    image = painterResource(id = R.drawable.late),
                    heading = "Late Fee",
                    height = 120,
                    width = 120,
                    imageHeight = 33,
                    imageWidth = 33,
                    textHeight = 13,
                    onClick = { navHostController.navigate("addLateFees") })

            }
            Spacer(modifier = Modifier.height(20.dp))

            GradeSelectedButton(grade = "Others", onClick = {})
            Spacer(modifier = Modifier.height(15.dp))


            // others
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clickable { launcher.launch("application/pdf") },
                elevation = CardDefaults.cardElevation(12.dp),
                colors = CardDefaults.cardColors(colorResource(id = R.color.secondary_blue))
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                )
                {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.result),
                            contentDescription = "",
                            modifier = Modifier
                                .width(33.dp)
                                .height(33.dp),
                            tint = Color.White
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            "Select Result PDF",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(5.dp))
            SaveUploadButton(title = "Upload Result") {
                uploadResultViewModel.selectPdfAndUpload()
                Toast.makeText(context, "Result Uploaded", Toast.LENGTH_SHORT).show()
            }
        }
    }

}