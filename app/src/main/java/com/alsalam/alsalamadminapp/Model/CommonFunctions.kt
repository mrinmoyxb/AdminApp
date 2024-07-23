package com.alsalam.alsalamadminapp.Model

import java.util.Random

fun randomStringGenerator(): String {
    val charPool: List<Char> = ('0'..'9') + ('a'..'z') + ('A'..'Z')
    val random = Random()
    val stringBuffer = StringBuffer(5)
    repeat(5) {
        val randomIndex = random.nextInt(charPool.size)
        stringBuffer.append(charPool[randomIndex])
    }
    return stringBuffer.toString()
}