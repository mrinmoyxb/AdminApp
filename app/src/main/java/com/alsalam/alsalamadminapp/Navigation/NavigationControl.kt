package com.alsalam.alsalamadminapp.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alsalam.alsalamadminapp.View.AddStudentScreen.AddStudentScreen
import com.alsalam.alsalamadminapp.View.AddStudentScreen.AllClassesScreen
import com.alsalam.alsalamadminapp.View.AddStudentScreen.EditStudentScreen
import com.alsalam.alsalamadminapp.View.AddStudentScreen.GradeWiseDisplayScreen
import com.alsalam.alsalamadminapp.View.AddStudentScreen.StudentDisplayDetailScreen
import com.alsalam.alsalamadminapp.View.ClassRoutineScreen.ClassRoutineScreen
import com.alsalam.alsalamadminapp.View.DailyTrackingScreen.DailyTrackingScreen
import com.alsalam.alsalamadminapp.View.DailyTrackingScreen.PreviousPaymentScreen
import com.alsalam.alsalamadminapp.View.DailyTrackingScreen.TodayAdmission
import com.alsalam.alsalamadminapp.View.DailyTrackingScreen.TodayExpenditure
import com.alsalam.alsalamadminapp.View.DailyTrackingScreen.TodayHostelFees
import com.alsalam.alsalamadminapp.View.DailyTrackingScreen.TodayLateFee
import com.alsalam.alsalamadminapp.View.DailyTrackingScreen.TodayOtherFee
import com.alsalam.alsalamadminapp.View.DailyTrackingScreen.TodayTuitionFee
import com.alsalam.alsalamadminapp.View.FeeScreen.AddHostelFeeScreen
import com.alsalam.alsalamadminapp.View.FeeScreen.AddLateFeeScreen
import com.alsalam.alsalamadminapp.View.FeeScreen.AddOtherFeeScreen
import com.alsalam.alsalamadminapp.View.FeeScreen.AddTuitionFeeScreen
import com.alsalam.alsalamadminapp.View.FeeScreen.AdmissionFee.AdmissionFeeScreen
import com.alsalam.alsalamadminapp.View.FeeScreen.ExpenditureScreen
import com.alsalam.alsalamadminapp.View.FeeScreen.FeeScreen
import com.alsalam.alsalamadminapp.View.FeeScreen.MonthlyFeeScreen.AdmissionFeeDisplayScreen
import com.alsalam.alsalamadminapp.View.FeeScreen.MonthlyFeeScreen.HostelFeeScreen
import com.alsalam.alsalamadminapp.View.FeeScreen.MonthlyFeeScreen.TuitionFeeScreen
import com.alsalam.alsalamadminapp.View.FestivalScreen.FestivalScreen
import com.alsalam.alsalamadminapp.View.HolidayScreen.HolidayScreen
import com.alsalam.alsalamadminapp.View.HomeScreen.HomeScreen
import com.alsalam.alsalamadminapp.View.NoticeScreen.NoticeScreen
import com.alsalam.alsalamadminapp.View.ResultScreen.ResultScreen
import com.alsalam.alsalamadminapp.View.TeachingStuff.AddSalaryScreen
import com.alsalam.alsalamadminapp.View.TeachingStuff.AddTeachingStaff
import com.alsalam.alsalamadminapp.View.TeachingStuff.AllSubjectsScreen
import com.alsalam.alsalamadminapp.View.TeachingStuff.SubjectWiseTeacher
import com.alsalam.alsalamadminapp.View.TeachingStuff.TeacherDetailSalary
import com.alsalam.alsalamadminapp.ViewModel.AddStaffViewModel
import com.alsalam.alsalamadminapp.ViewModel.AddStudentViewModel
import com.alsalam.alsalamadminapp.ViewModel.AdmissionFeeViewModel
import com.alsalam.alsalamadminapp.ViewModel.DailyTrackingViewModel.BalanceViewModel
import com.alsalam.alsalamadminapp.ViewModel.DailyTrackingViewModel.DailyCollectionViewModel
import com.alsalam.alsalamadminapp.ViewModel.EditDetailsViewModel
import com.alsalam.alsalamadminapp.ViewModel.MonthlyPaymentViewModel
import com.alsalam.alsalamadminapp.ViewModel.PaymentViewModel
import com.alsalam.alsalamadminapp.ViewModel.PdfUploadAndRetrieveViewModel
import com.alsalam.alsalamadminapp.ViewModel.TeacherSalaryViewModel

@Preview
@Composable
fun MainScreen(){
    val navController = rememberNavController()

    val viewModel: AddStudentViewModel = viewModel()
    val paymentViewModel: PaymentViewModel = viewModel()

    val admissionViewModel: AdmissionFeeViewModel = viewModel()
    val pdfViewModel: PdfUploadAndRetrieveViewModel = viewModel()

    val teacherViewModel: AddStaffViewModel = viewModel()

    val dailyCollectionViewModel: DailyCollectionViewModel = viewModel()

    val balanceViewModel: BalanceViewModel = viewModel()

    val teacherSalaryViewModel: TeacherSalaryViewModel = viewModel()

    val monthlyPaymentViewModel: MonthlyPaymentViewModel = viewModel()

    val editStudentDetails: EditDetailsViewModel = viewModel()

    NavHost(navController = navController, startDestination = "homeScreen"){

        composable(route = "homeScreen"){
            HomeScreen(navController = navController)
        }

        composable(route = "allClassesScreen"){
            AllClassesScreen(navController = navController, viewModel)
        }

        composable(route = "displayStudentGradeWise"){
            GradeWiseDisplayScreen(viewModel, navController, paymentViewModel, admissionViewModel, pdfViewModel,
                dailyCollectionViewModel, monthlyPaymentViewModel)
        }

        composable(route = "displayStudentDetail"){
            StudentDisplayDetailScreen(paymentViewModel, navController, pdfViewModel, editViewModel = editStudentDetails)
        }

        composable(route = "classRoutineScreen"){
            ClassRoutineScreen()
        }

        composable(route = "holidayScreen"){
            HolidayScreen()
        }

        composable(route = "resultScreen"){
            ResultScreen()
        }

        composable(route = "noticeScreen"){
            NoticeScreen()
        }

        composable(route = "festivalScreen"){
            FestivalScreen()
        }

        composable(route = "addStudent"){
            AddStudentScreen()
        }

        composable(route="feesScreen"){
            FeeScreen(navController)
        }

        // add hostel fee
        composable(route = "addHostelFees"){
            AddHostelFeeScreen(paymentViewModel, dailyCollectionViewModel, balanceViewModel, monthlyPaymentViewModel)
        }

        // add tuition fee
        composable(route = "addTuitionFees"){
            AddTuitionFeeScreen(paymentViewModel, dailyCollectionViewModel, balanceViewModel, monthlyPaymentViewModel)
        }

        // add other fee
        composable(route = "addOtherFees"){
            AddOtherFeeScreen(paymentViewModel, dailyCollectionViewModel, balanceViewModel, monthlyPaymentViewModel)
        }

        // add late fee
        composable(route = "addLateFees"){
            AddLateFeeScreen(paymentViewModel, dailyCollectionViewModel, balanceViewModel)
        }

        // edit
        composable(route = "editStudent"){
            EditStudentScreen(editStudentDetails)
        }

        composable(route="hostelFees"){
            HostelFeeScreen(navController = navController)
        }

        composable(route="admissionFees"){
            AdmissionFeeDisplayScreen(navController = navController)
        }

        composable(route="tuitionFees"){
            TuitionFeeScreen(navController = navController)
        }

        composable(route = "expenditureScreen"){
            ExpenditureScreen(balanceViewModel)
        }

        // add admission fee
        composable(route = "admissionFeeScreen"){
            AdmissionFeeScreen(admissionViewModel, dailyCollectionViewModel, balanceViewModel, monthlyPaymentViewModel, paymentViewModel)
        }


        // Staff
        composable(route = "allSubjectsScreen"){
            AllSubjectsScreen(navController, teacherViewModel)
        }

        composable(route = "addTeachingStaff"){
            AddTeachingStaff()
        }

        composable(route = "subjectWiseTeacher"){
            SubjectWiseTeacher(navController,teacherViewModel, teacherSalaryViewModel)
        }

        composable(route = "teacherDetailSalary"){
           TeacherDetailSalary(navController, teacherSalaryViewModel)
        }

        composable(route = "AddSalaryScreen"){
           AddSalaryScreen(teacherSalaryViewModel, balanceViewModel)
        }



        // Daily Tracking
        composable(route="dailyTrackingScreen"){
            DailyTrackingScreen(navController, balanceViewModel)
        }
        composable(route = "todayAdmissionFeeCollected"){
            TodayAdmission()
        }
        composable(route = "todayHostelFeesCollected"){
            TodayHostelFees()
        }
        composable(route = "todayTuitionFeeCollected"){
            TodayTuitionFee()
        }
        composable(route = "todayExpenditure"){
            TodayExpenditure()
        }
        composable(route = "todayOtherFeeCollected"){
            TodayOtherFee()
        }
        composable(route = "todayLateFeeCollected"){
            TodayLateFee()
        }
        composable(route = "previousPayments"){
            PreviousPaymentScreen()
        }

    }
}