package com.alsalam.alsalamadminapp.View.Components

import android.annotation.SuppressLint
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.alsalam.alsalamadminapp.Model.Teacher
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.ViewModel.AddStudentViewModel
import com.alsalam.alsalamadminapp.ViewModel.PaymentViewModel
import kotlin.math.max


// Floating Button
@Composable
fun FloatingButton(navController: NavHostController, route: String){
    FloatingActionButton(onClick = { navController.navigate(route)},
        shape = CircleShape,
        containerColor = Color.Black,
        modifier = Modifier
            .height(70.dp)
            .width(70.dp)
    ) {
        Icon(painter = painterResource(id = R.drawable.add),
            contentDescription = "Add Student",
            modifier = Modifier
                .height(20.dp)
                .width(20.dp),
            tint = Color.White
        )
    }
}


// Class Grades
@Composable
fun GradeCard(heading: String = "Class", grade: String, fontSize: Int = 20, onClick: () -> Unit){

    val context = LocalContext.current

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .background(Color.Transparent)
        .clickable {
            onClick()
        },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(colorResource(id = R.color.secondary_blue)))
    {
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center)
        {
            Text("$heading $grade",
                fontSize=fontSize.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
    }
}



// upload card
@Composable
fun UploadCard(title: String, onClick: () -> Unit){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .clickable {
            onClick()
        },
        colors = CardDefaults.cardColors(colorResource(id = R.color.secondary_blue)))
    {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = R.drawable.add),
                contentDescription = "",
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp),
                tint = Color.White
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White)
        }

    }
}


// Save/Upload Button
@Composable
fun SaveUploadButton(title: String, onClick: () -> Unit){
    Button(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.secondary_blue)),
        onClick = {onClick()})
    {
        Text(title)
    }
}


// custom cards
@Composable
fun CustomCard(image: Painter, heading: String, height: Int = 120, width: Int = 120, imageHeight: Int = 42,
               imageWidth: Int = 42, textHeight: Int = 15, onClick: () -> Unit)
{
    Card(modifier = Modifier
        .height(height.dp)
        .width(width.dp)
        .clickable {
            onClick()
        },
        elevation = CardDefaults.cardElevation(12.dp),
        colors = CardDefaults.cardColors(colorResource(id = R.color.secondary_blue))
    )
    {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center)
        {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = image, contentDescription = "",
                    modifier = Modifier
                        .width(imageWidth.dp)
                        .height(imageHeight.dp),
                    tint = Color.White
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(heading,
                    fontSize = textHeight.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White)
            }
        }
    }
}


// custom top bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(text: String) {
    TopAppBar(
        title = { Text(text, color = Color.White, fontWeight = FontWeight.Medium) },
        colors = TopAppBarDefaults.topAppBarColors(colorResource(id = R.color.secondary_blue)),
        modifier = Modifier.fillMaxWidth()
    )
}


// Each student Card
@Composable
fun StudentDisplayCard(name: String, studentId: String, roll: String, dob: String,  imageUrl:String = "", onClick: () -> Unit){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(160.dp)
        .clickable {
            onClick()
        },
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(colorResource(id = R.color.secondary_blue))
        )
    {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)){
            Column(modifier = Modifier.width(260.dp), verticalArrangement = Arrangement.Top){
                Spacer(modifier = Modifier.height(30.dp))
                Text(name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 22.sp, maxLines = 1)

                Spacer(modifier = Modifier.height(12.dp))
                Text("SID: $studentId", color = Color.White, fontWeight = FontWeight.Normal, fontSize = 17.sp, maxLines = 1)
                Text("Roll.No: $roll", color = Color.White, fontWeight = FontWeight.Normal, fontSize = 17.sp)
                Text("DOB: $dob", color = Color.White, fontWeight = FontWeight.Normal, fontSize = 17.sp)
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ){
               Box(
                   modifier = Modifier
                       .height(85.dp)
                       .width(85.dp)
                       .clip(CircleShape)
                       .background(Color.White),
                   contentAlignment = Alignment.Center
               ){
                   if(imageUrl!="") {
                       AsyncImage(
                           model = imageUrl,
                           contentDescription = "Student Image",
                           contentScale = ContentScale.Crop, // Adjust as needed
                           modifier = Modifier
                               .height(85.dp)
                               .width(85.dp)
                       )
                   } else {
                       Text("Pic")
                    }
               }
            }
        }
    }
}


// select card
@Composable
fun SelectCard(heading: String, height: Int = 100, roundedCornerShape: Int = 15, color: Int = R.color.secondary_blue, onClick: () -> Unit){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(height.dp)
        .clickable {
            onClick()
        },
        shape = RoundedCornerShape(roundedCornerShape.dp),
        colors = CardDefaults.cardColors(colorResource(id = color)),
        elevation = CardDefaults.cardElevation(10.dp)
    )
    {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center)
        {
            Text(heading.toString(), fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun TeacherDisplayCard(name: String, qualification: String, address: String, dateOfAppointment: String, amount: String, bioData: String, onClick: () -> Unit){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(220.dp)
        .clickable {
            onClick()
        },
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(colorResource(id = R.color.secondary_blue))
    )
    {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp),
            verticalArrangement = Arrangement.Top)
        {
        Spacer(modifier = Modifier.height(10.dp))
            Text(name.uppercase(), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 25.sp)
            Text(qualification, color = Color.White, fontWeight = FontWeight.Medium, fontSize = 22.sp)

            Spacer(modifier = Modifier.height(10.dp))
            Text("Appointment: $dateOfAppointment", color = Color.White, fontWeight = FontWeight.Normal, fontSize = 18.sp)
            Text("Address: $address", color = Color.White, fontWeight = FontWeight.Normal, fontSize = 18.sp, overflow = TextOverflow.Clip, maxLines = 2)
            Text("Bio Data: $bioData", color = Color.White, fontWeight = FontWeight.Normal, fontSize = 18.sp)
            Text("Salary: ₹ $amount", color = Color.White, fontWeight = FontWeight.Normal, fontSize = 18.sp)
    }

    }
}




@Composable
fun StudentCardForAdminPayment(name: String, studentId: String, grade: String, roll: String, feesPaid: Boolean, approvedByAdmin: Boolean, onClick: () -> Unit){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(230.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(colorResource(id = R.color.secondary_blue))
    )
    {
        Column(modifier = Modifier.fillMaxWidth().padding(10.dp), verticalArrangement = Arrangement.Top) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 22.sp, maxLines = 1)

            Spacer(modifier = Modifier.height(5.dp))
            Text("Student id: $studentId", color = Color.White, fontWeight = FontWeight.Normal, fontSize = 17.sp)
            Text("Grade: $grade", color = Color.White, fontWeight = FontWeight.Normal, fontSize = 17.sp, maxLines = 1)
            Text("Roll.No: $roll", color = Color.White, fontWeight = FontWeight.Normal, fontSize = 17.sp)
            Text("Fees Paid: ${if (feesPaid) "Yes" else "No"}", color = if (feesPaid) Color.Green else Color.Red, fontWeight = FontWeight.SemiBold, fontSize = 17.sp)
            Text("Approved: ${if (approvedByAdmin) "Yes" else "No"}", color = if (approvedByAdmin) Color.Green else Color.Red, fontWeight = FontWeight.SemiBold, fontSize = 17.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.End
        )
        {
            Card(
                modifier = Modifier
                    .width(110.dp)
                    .height(60.dp)
                    .background(Color.Transparent)
                    .clickable{onClick()},
                shape = RoundedCornerShape(20.dp)
            )
            {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Approve", fontWeight = FontWeight.SemiBold, color = Color.Black)
                }
            }
        }

    }
}

@Preview
@Composable
fun dis(){
    StudentCardForAdminPayment(
        name = "A",
        studentId = "DDD",
        grade = "A",
        roll = "A",
        feesPaid = true,
        approvedByAdmin = true
    ) {

    }
}
