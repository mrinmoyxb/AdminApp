package com.alsalam.alsalamadminapp.View.FeeScreen

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextInputService
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.ViewModel.AddStudentViewModel
import com.alsalam.alsalamadminapp.ViewModel.PaymentViewModel
import com.alsalam.alsalamadminapp.ViewModel.TeacherSalaryViewModel

// drop down menu
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CustomDropDownMenuFeesPaid(viewModel: PaymentViewModel) {
    var isExpanded by remember { mutableStateOf(false) }
    val options: List<String> = listOf("Yes", "No")
    var category by remember { mutableStateOf(options[1]) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val myTextInputService: TextInputService? = null

    when(category){
        "Yes" -> viewModel.currentIsFeePaid.value = true
        "No" -> viewModel.currentIsFeePaid.value = false
    }

    CompositionLocalProvider(
        LocalTextInputService provides myTextInputService
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .height(65.dp)
                .clickable { keyboardController?.hide() }
                .background(color = Color.Transparent, shape = RoundedCornerShape(10.dp))
        ) {
            ExposedDropdownMenuBox(expanded = isExpanded,
                onExpandedChange = { isExpanded = !isExpanded }) {
                TextField(
                    value = category, onValueChange = {},
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxSize(),
                    textStyle = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium, color = Color.White
                    ),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                    readOnly = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = colorResource(id = R.color.secondary_blue),
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.None,
                    )
                )
                ExposedDropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false }) {
                    options.forEach { label ->
                        DropdownMenuItem(text = {
                            Text(
                                label,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )
                        }, onClick = {
                            category = label
                            isExpanded = false
                        },
                            colors = MenuDefaults.itemColors(colorResource(id = R.color.secondary_blue))
                        )
                    }
                }
            }
        }
    }
}

// horizontal grade
@Composable
fun CustomHorizontalCard(grade: String, onClick:()-> Unit){
    Card(modifier = Modifier
        .height(100.dp)
        .width(100.dp)
        .padding(5.dp)
        .clickable {
            onClick()
        },
        colors = CardDefaults.cardColors(colorResource(id = R.color.secondary_blue)),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(10.dp)
        )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(grade, fontSize = 23.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
            Text("Grade", fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color.White)
        }
    }
}

// grade display button
@Composable
fun GradeSelectedButton(grade: String, width: Int = 90, onClick: () -> Unit){
    Card(
        modifier = Modifier
            .width(width.dp)
            .height(30.dp)
            .background(Color.Transparent)
            .clickable{
                onClick()
            },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(Color(0xFFEEEEEE)),
        elevation = CardDefaults.cardElevation(10.dp)
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("$grade", fontSize = 13.sp)
        }
    }
}

// fess paid card
@Composable
fun FeesPaidStudentCard(name: String, roll: String, amount: String, paid: Boolean, date: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(colorResource(id = R.color.secondary_blue))
    ) {
        Row(modifier = Modifier.fillMaxSize().padding(all = 10.dp)) {
            Column(
                modifier = Modifier
                    .width(250.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(name.uppercase(), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 22.sp)
                Text(roll, color = Color.White, fontWeight = FontWeight.Normal, fontSize = 22.sp)
                Text("₹ $amount", color = Color.White, fontWeight = FontWeight.Normal, fontSize = 22.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(if (paid) "PAID" else "NOT PAID", color = if (paid) Color.Green else Color.Red, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(if (paid) date else "", color = Color.White, fontWeight = FontWeight.Normal, fontSize = 12.sp)
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
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CustomDropDownMenuGrade(viewModel: AddStudentViewModel) {
    var isExpanded by remember { mutableStateOf(false) }
    val options: List<String> = listOf("Ankur", "PP", "Class I", "Class II", "Class III", "Class IV", "Class V", "Class VI", "Class VII", "Class VIII",
        "Class IX", "Class X", "Class XI(Arts)", "Class XI(Science)", "Class XII(Arts)", "Class XII(Science)")
    var category by remember { mutableStateOf(options[0]) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val myTextInputService: TextInputService? = null

    when(category){
        "Ankur" -> viewModel.className.value = "Ankur"
        "PP" -> viewModel.className.value = "PP"
        "Class I" -> viewModel.className.value = "I"
        "Class II" -> viewModel.className.value = "II"
        "Class III" -> viewModel.className.value = "III"
        "Class IV" -> viewModel.className.value = "IV"
        "Class V" -> viewModel.className.value = "V"
        "Class VI" -> viewModel.className.value = "VI"
        "Class VII" -> viewModel.className.value = "VII"
        "Class VIII" -> viewModel.className.value = "VIII"
        "Class IX" -> viewModel.className.value = "IX"
        "Class X" -> viewModel.className.value = "X"
        "Class XI(Arts)" -> viewModel.className.value = "XI(Arts)"
        "Class XI(Science)" -> viewModel.className.value = "XI(Science)"
        "Class XII(Arts)" -> viewModel.className.value = "XII(Arts)"
        "Class XII(Science)" -> viewModel.className.value = "XII(Science)"
    }

    CompositionLocalProvider(
        LocalTextInputService provides myTextInputService
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .height(65.dp)
                .clickable { keyboardController?.hide() }
                .background(color = Color.Transparent, shape = RoundedCornerShape(10.dp))
        ) {
            ExposedDropdownMenuBox(expanded = isExpanded,
                onExpandedChange = { isExpanded = !isExpanded }) {
                TextField(
                    value = category, onValueChange = {},
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxSize(),
                    textStyle = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium, color = Color.White
                    ),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                    readOnly = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = colorResource(id = R.color.secondary_blue),
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.None,
                    )
                )
                ExposedDropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false }) {
                    options.forEach { label ->
                        DropdownMenuItem(text = {
                            Text(
                                label,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )
                        }, onClick = {
                            category = label
                            isExpanded = false
                        },
                            colors = MenuDefaults.itemColors(colorResource(id = R.color.secondary_blue))
                        )
                    }
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CustomDropDownMenuMonth(viewModel: TeacherSalaryViewModel) {
    var isExpanded by remember { mutableStateOf(false) }
    val months: List<String> = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec")
    var category by remember { mutableStateOf(months[0]) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val myTextInputService: TextInputService? = null

    when(category){
        "Jan" -> viewModel.currentMonth.value = "Jan"
        "Feb" -> viewModel.currentMonth.value = "Fev"
        "Mar" -> viewModel.currentMonth.value = "Mar"
        "Apr" -> viewModel.currentMonth.value = "Apr"
        "May" -> viewModel.currentMonth.value = "May"
        "Jun" -> viewModel.currentMonth.value = "Jun"
        "Jul" -> viewModel.currentMonth.value = "Jul"
        "Aug" -> viewModel.currentMonth.value = "Aug"
        "Sept" -> viewModel.currentMonth.value = "Sept"
        "Oct" -> viewModel.currentMonth.value = "Oct"
        "Nov" -> viewModel.currentMonth.value = "Nov"
        "Dec" -> viewModel.currentMonth.value = "Dec"
    }

    CompositionLocalProvider(
        LocalTextInputService provides myTextInputService
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .height(65.dp)
                .clickable { keyboardController?.hide() }
                .background(color = Color.Transparent, shape = RoundedCornerShape(10.dp))
        ) {
            ExposedDropdownMenuBox(expanded = isExpanded,
                onExpandedChange = { isExpanded = !isExpanded }) {
                TextField(
                    value = category, onValueChange = {},
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxSize(),
                    textStyle = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium, color = Color.White
                    ),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                    readOnly = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = colorResource(id = R.color.secondary_blue),
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.None,
                    )
                )
                ExposedDropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false }) {
                    months.forEach { label ->
                        DropdownMenuItem(text = {
                            Text(
                                label,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )
                        }, onClick = {
                            category = label
                            isExpanded = false
                        },
                            colors = MenuDefaults.itemColors(colorResource(id = R.color.secondary_blue))
                        )
                    }
                }
            }
        }
    }
}