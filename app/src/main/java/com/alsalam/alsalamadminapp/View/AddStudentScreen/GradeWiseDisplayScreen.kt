package com.alsalam.alsalamadminapp.View.AddStudentScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.alsalam.alsalamadminapp.View.Components.StudentDisplayCard
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.View.Components.CustomTopBar
import com.alsalam.alsalamadminapp.ViewModel.AddStudentViewModel
import com.alsalam.alsalamadminapp.ViewModel.AdmissionFeeViewModel
import com.alsalam.alsalamadminapp.ViewModel.DailyTrackingViewModel.DailyCollectionViewModel
import com.alsalam.alsalamadminapp.ViewModel.MonthlyPaymentViewModel
import com.alsalam.alsalamadminapp.ViewModel.PaymentViewModel
import com.alsalam.alsalamadminapp.ViewModel.PdfUploadAndRetrieveViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GradeWiseDisplayScreen(viewModel: AddStudentViewModel, navController: NavHostController, paymentViewModel: PaymentViewModel,
                           admissionVViewModel: AdmissionFeeViewModel, pdfUpload: PdfUploadAndRetrieveViewModel,
                           dailyExpenseViewModel: DailyCollectionViewModel, monthlyPayment: MonthlyPaymentViewModel
)
{
    val students by viewModel.students.observeAsState(emptyList())
    val gradeSelected by viewModel._gradeSelected.collectAsState("")
    val context = LocalContext.current

    Scaffold(topBar = { CustomTopBar(text = "Students of class $gradeSelected")})
    {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.secondary_gray1))
                    .padding(10.dp)
            )
            {
                item {
                    Spacer(modifier = Modifier.height(85.dp))
                    students.forEach { student ->
                        StudentDisplayCard(name = student.studentName,
                            studentId = student.studentId,
                            roll = student.rollNo,
                            dob = student.dateOfBirth,
                            onClick = {
                                navController.navigate("displayStudentDetail")

                                // payment
                                paymentViewModel.currentActiveName.value = student.studentName
                                paymentViewModel.currentActiveRollNo.value = student.rollNo
                                paymentViewModel.currentActiveDob.value = student.dateOfBirth
                                paymentViewModel.gradeSelected.value = gradeSelected
                                paymentViewModel.currentStudentId.value = student.studentId
                                paymentViewModel.fatherName.value = student.fatherName
                                paymentViewModel.motherName.value = student.motherName
                                paymentViewModel.village.value = student.village
                                paymentViewModel.postOffice.value = student.postOffice
                                paymentViewModel.policeStation.value = student.policeStation
                                paymentViewModel.pin.value = student.pin
                                paymentViewModel.mobileNo.value = student.mobileNo
                                paymentViewModel.admissionDate.value = student.admissionDate
                                paymentViewModel.admissionFees.value = student.admissionFees
                                paymentViewModel.monthlyFees.value = student.monthlyFees


                                // admission
                                admissionVViewModel.gradeSelected.value = gradeSelected
                                admissionVViewModel.currentActiveRollNo.value = student.rollNo

                                // result
                                pdfUpload.currentActiveRollNo.value = student.rollNo
                                pdfUpload.currentStudentName.value = student.studentName
                                pdfUpload.gradeSelected.value = gradeSelected

                                // daily expense
                                dailyExpenseViewModel.studentName.value =  student.studentName
                                dailyExpenseViewModel.grade.value = gradeSelected.toString()

                                // monthly payment
                                monthlyPayment.currentActiveName.value = student.studentName
                                monthlyPayment.currentActiveRollNo.value = student.rollNo
                                monthlyPayment.gradeSelected.value = gradeSelected
                                monthlyPayment.studentId.value = student.studentId
                            })

                        Spacer(modifier = Modifier.height(8.dp))
                    }

                }
            }
        }
}



