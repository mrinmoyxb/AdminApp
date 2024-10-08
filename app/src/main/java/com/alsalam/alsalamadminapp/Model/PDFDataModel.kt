package com.alsalam.alsalamadminapp.Model


data class PDFDataModel(
    val fileName: String,
    val uploadedDate: Long,
    val url: String? = null
){
    constructor(): this("", 0)
}

data class ResultPDFDataModel(
    val studentId: String,
    val uploadedDate: Long,
    val url: String? = null
){
    constructor(): this("", 0)
}
