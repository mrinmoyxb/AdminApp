package com.alsalam.alsalamadminapp.Model

// STUDENT----------------------------------------------------------------------------------------//
data class StudentInfo(
    val studentName: String,
    val rollNo: String,
    val dateOfBirth: String,
    var imageUrl: String? = null
){
    constructor() : this("", "", "","")
}



// PAYMENTS ----------------------------------------------------------------------------------------//
enum class PaymentTypes{
    AdmissionFees,
    TuitionFees,
    HostelFees,
    DefaultFees
}

data class StudentFee(
    val studentName: String,
    val studentRollNo: String,
    val studentPaymentFor: PaymentTypes,
    val studentPaymentAmount: Double,
    val date: Long,
    val studentFeesPaid: Boolean
){
    constructor(): this("","", PaymentTypes.DefaultFees, 0.0, 0, false)
}



// EXPENDITURE--------------------------------------------------------------------------------------//
data class Expenditure(
    val category: String,
    val amount: Double,
    val date: Long
){
    constructor(): this("", 0.0, 0)
}



// ADMISSION-----------------------------------------------------------------------------------------//
data class AdmissionAmount(
    val total: Double,
    val date: Long,
    val isPaid: Boolean
)



// EACH STUDENT RESULT------------------------------------------------------------------------------//
data class StudentResult(
    val nameOfStudent: String,
    val rollNo: String,
    var pdfUrl: String? = null
)



// TEACHING STUFF-----------------------------------------------------------------------------------//
enum class Subjects{
    English,
    Hindi,
    Mathematics,
    PhysicalEducation,
    Science,
    SocialScience,
    NULL
}

data class Teacher(
    val teacherName: String,
    val subject: Subjects,
    val salary: Double,
    val addressOfTeacher: String,
    val dateOfAppointment: String,
    val qualification: String,
    val bioData: String
){
    constructor(): this("", Subjects.NULL, 0.0, "", "", "", "")
}



// DAILY EXPENSE------------------------------------------------------------------------------------//
data class DailyExpense(
    val studentName: String,
    val paymentTypes: PaymentTypes,
    val amount: Double,
    val grade: String,
    val date: Long
){
    constructor(): this("", PaymentTypes.DefaultFees, 0.0, "", 0)
}



// BALANCE -----------------------------------------------------------------------------------------//
data class Balance(
    val amount: Double
){
    constructor(): this(0.0)
}