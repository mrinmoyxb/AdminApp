package com.alsalam.alsalamadminapp.Model

// STUDENT----------------------------------------------------------------------------------------//
data class StudentInfo(
    val studentName: String,
    val studentId: String,
    val rollNo: String,
    val dateOfBirth: String,
    // additional
    var fatherName: String,
    var motherName: String,
    var village: String,
    var postOffice: String,
    var policeStation: String,
    var district: String,
    var grade: String,
    var pin: String,
    var mobileNo: String,
    var admissionDate: String,
    var admissionFees: String,
    var monthlyFees: String,
    var imageUrl: String? = null,

){
    constructor() : this("", "", "","", "", "", "", "", "", "", "", "", "", "", "", "", "")
}


// EDIT STUDENT----------------------------------------------------------------------------------------//
data class EditStudentInfo(
    val studentName: String,
    val rollNo: String,
    val dateOfBirth: String,
    var fatherName: String,
    var motherName: String,
    var village: String,
    var postOffice: String,
    var policeStation: String,
    var district: String,
    var grade: String,
    var pin: String,
    var mobileNo: String,
    var admissionDate: String,
    var admissionFees: String,
    var monthlyFees: String,

    ){
    constructor() : this("", "","", "", "", "", "", "", "", "", "", "", "", "", "")
    fun toMap(): Map<String, Any> {
        return mapOf(
            "studentName" to studentName,
            "rollNo" to rollNo,
            "dateOfBirth" to dateOfBirth,
            "fatherName" to fatherName,
            "motherName" to motherName,
            "village" to village,
            "postOffice" to postOffice,
            "policeStation" to policeStation,
            "district" to district,
            "grade" to grade,
            "pin" to pin,
            "mobileNo" to mobileNo,
            "admissionDate" to admissionDate,
            "admissionFees" to admissionFees,
            "monthlyFees" to monthlyFees
        )
    }
}


// PAYMENTS ----------------------------------------------------------------------------------------//
enum class PaymentTypes{
    AdmissionFees,
    TuitionFees,
    HostelFees,
    OtherFees,
    LateFees,
    DefaultFees
}

data class StudentFee(
    val studentName: String,
    val studentRollNo: String,
    val studentPaymentFor: PaymentTypes,
    val studentPaymentAmount: Double,
    val date: Long,
    val studentFeesPaid: Boolean,
    val dateSetByAdmin: String
){
    constructor(): this("","", PaymentTypes.DefaultFees, 0.0, 0, false, "")
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
enum class Designation{
    HostelWarden,
    Librarian,
    CookMan,
    Peon,
    Chowkidar,
    SecurityGuard,
    Clerk,
    Accountant,
    Assistant,
    Teacher,
    VicePrincipal,
    Principal,
    NULL
}
enum class Subjects{
    GeneralMaths,
    GeneralScience,
    GeneralEnglish,
    SocialScience,
    AdvanceMathematics,
    Arabic,
    Sanskrit,
    MIL,
    GarmentsDesigning,
    LogicAndPhilosophyEducation,
    Economics,
    PoliticalScience,
    Biology,
    Zoology,
    Physics,
    Chemistry,
    EVS,
    SwadeshAdhyayan,
    Hindi,
    ArtEducation,
    PhysicalEducation,
    NULL
}

data class Teacher(
    val teacherName: String,
    val subject: Subjects,
    val designation: Designation,
    val salary: Double,
    val addressOfTeacher: String,
    val dateOfAppointment: String,
    val qualification: String,
    val bioData: String
){
    constructor(): this("", Subjects.NULL, Designation.NULL, 0.0, "", "", "", "")
}


data class TeacherSalary(
    val teacherName: String,
    val subject: Subjects,
    val salary: Double,
    val qualification: String,
    val month: String,
    val date: Long,
    val setDateByAdmin: String
){
    constructor(): this("", Subjects.NULL, 0.0, "", "", 0, "")
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



// MONTHLY PAYMENT ---------------------------------------------------------------------------------//
data class MonthlyPayment(
    val studentName: String,
    val studentGrade: String,
    val studentRollNo: String,
    val studentPaymentFor: PaymentTypes,
    val studentPaymentAmount: Double,
    val date: Long,
    val studentFeesPaid: Boolean,

    // additional
    val approveByAdmin: Boolean = false,
    val studentId: String = "NA"
){
    constructor(): this("","", "", PaymentTypes.DefaultFees,
        0.0, 0, false)
}