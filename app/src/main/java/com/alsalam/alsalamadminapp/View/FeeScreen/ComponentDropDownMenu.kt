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
import com.alsalam.alsalamadminapp.Model.Designation
import com.alsalam.alsalamadminapp.Model.Subjects
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.ViewModel.AddStaffViewModel
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
        .height(125.dp)
        .width(125.dp)
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
            Text(grade, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
            Text("Class", fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color.White)
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


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CustomDropDownMenuTeacherSubject(viewModel: AddStaffViewModel) {
    var isExpanded by remember { mutableStateOf(false) }
    val subject: List<Subjects> = listOf(Subjects.GeneralMaths, Subjects.GeneralScience, Subjects.GeneralEnglish, Subjects.SocialScience, Subjects.AdvanceMathematics, Subjects.Arabic, Subjects.Sanskrit, Subjects.MIL,
        Subjects.GarmentsDesigning, Subjects.LogicAndPhilosophyEducation, Subjects.Economics, Subjects.PoliticalScience, Subjects.Biology, Subjects.Zoology, Subjects.Physics, Subjects.Chemistry, Subjects.EVS, Subjects.SwadeshAdhyayan, Subjects.Hindi, Subjects.ArtEducation, Subjects.PhysicalEducation, Subjects.NULL)
    var category by remember { mutableStateOf(subject[0]) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val myTextInputService: TextInputService? = null

    when(category){
        Subjects.GeneralMaths -> viewModel.subject.value = Subjects.GeneralMaths
        Subjects.GeneralScience -> viewModel.subject.value = Subjects.GeneralScience
        Subjects.GeneralEnglish -> viewModel.subject.value = Subjects.GeneralEnglish
        Subjects.SocialScience -> viewModel.subject.value = Subjects.SocialScience
        Subjects.AdvanceMathematics -> viewModel.subject.value = Subjects.AdvanceMathematics
        Subjects.Arabic-> viewModel.subject.value = Subjects.Arabic
        Subjects.Sanskrit-> viewModel.subject.value = Subjects.Sanskrit
        Subjects.MIL -> viewModel.subject.value = Subjects.MIL
        Subjects.GarmentsDesigning -> viewModel.subject.value = Subjects.GarmentsDesigning
        Subjects.LogicAndPhilosophyEducation -> viewModel.subject.value = Subjects.LogicAndPhilosophyEducation
        Subjects.Economics -> viewModel.subject.value = Subjects.Economics
        Subjects.PoliticalScience -> viewModel.subject.value = Subjects.PoliticalScience
        Subjects.Biology -> viewModel.subject.value = Subjects.Biology
        Subjects.Zoology -> viewModel.subject.value = Subjects.Zoology
        Subjects.Physics -> viewModel.subject.value =  Subjects.Physics
        Subjects.Chemistry -> viewModel.subject.value = Subjects.Chemistry
        Subjects.EVS-> viewModel.subject.value = Subjects.EVS
        Subjects.SwadeshAdhyayan-> viewModel.subject.value = Subjects.SwadeshAdhyayan
        Subjects.Hindi-> viewModel.subject.value = Subjects.Hindi
        Subjects.ArtEducation-> viewModel.subject.value = Subjects.ArtEducation
        Subjects.PhysicalEducation-> viewModel.subject.value = Subjects.PhysicalEducation
        Subjects.NULL-> viewModel.subject.value = Subjects.NULL
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
                    value = category.toString(), onValueChange = {},
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
                    subject.forEach { label ->
                        DropdownMenuItem(text = {
                            Text(
                                label.toString(),
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
fun CustomDropDownMenuDesignation(viewModel: AddStaffViewModel) {
    var isExpanded by remember { mutableStateOf(false) }
    val designation: List<Designation> = listOf(Designation.HostelWarden, Designation.Librarian, Designation.CookMan, Designation.Peon, Designation.Chowkidar,
        Designation.SecurityGuard, Designation.Clerk, Designation.Accountant, Designation.Assistant, Designation.Teacher, Designation.VicePrincipal, Designation.Principal, Designation.NULL)
    var category by remember { mutableStateOf(designation[0]) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val myTextInputService: TextInputService? = null

    when(category){
        Designation.HostelWarden -> viewModel.designation.value = Designation.HostelWarden
        Designation.Librarian -> viewModel.designation.value = Designation.Librarian
        Designation.CookMan -> viewModel.designation.value = Designation.CookMan
        Designation.Peon-> viewModel.designation.value = Designation.Peon
        Designation.Chowkidar -> viewModel.designation.value = Designation.Chowkidar
        Designation.SecurityGuard -> viewModel.designation.value = Designation.SecurityGuard
        Designation.Clerk -> viewModel.designation.value = Designation.Clerk
        Designation.Accountant -> viewModel.designation.value = Designation.Accountant
        Designation.Assistant -> viewModel.designation.value =  Designation.Assistant
        Designation.Teacher -> viewModel.designation.value = Designation.Teacher
        Designation.VicePrincipal-> viewModel.designation.value = Designation.VicePrincipal
        Designation.Principal-> viewModel.designation.value =  Designation.Principal
        Designation.NULL -> viewModel.designation.value = Designation.NULL
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
                    value = category.toString(), onValueChange = {},
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
                    designation.forEach { label ->
                        DropdownMenuItem(text = {
                            Text(
                                label.toString(),
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