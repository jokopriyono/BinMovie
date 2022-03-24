package com.bin.movie

import android.text.format.DateUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.toCalendar(pattern: String): Calendar? {
    val dateFormat = SimpleDateFormat(pattern, Locale("id", "ID"))
    return try {
        val calendar = Calendar.getInstance()
        calendar.time = dateFormat.parse(this) ?: Date()
        calendar
    } catch (e: ParseException) {
        throw(e)
    }
}

fun String.yyyyMMddThhmmssToDate(): Date? {
    return try {
        val currentFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss", Locale("id", "ID"))
        currentFormat.timeZone = TimeZone.getDefault()
        currentFormat.parse(this)
    } catch (e: Exception) {
        null
    }
}

fun getToday(): Date {
    val currentDate = Calendar.getInstance()
    return currentDate.time
}

fun getTodayTimeMillSecond(): Long {
    val currentDate = Calendar.getInstance()
    return currentDate.timeInMillis
}

/** Result 31 Jan 2021 **/
fun Date.formatddMMMyyyy(): String? {
    val convert = SimpleDateFormat("dd MMM yyyy", Locale("id", "ID"))
    return convert.format(this)
}

/** Result 31 Jan 2021 - 22:00 **/
fun Date.formatddMMMyyyyhhmm(): String? {
    val convert = SimpleDateFormat("dd MMM yyyy - kk:mm", Locale("id", "ID"))
    return convert.format(this)
}

/** Result 31 January 2021 | 22:00 **/
fun Date.formatddMMMMyyyyhhmm(): String? {
    val convert = SimpleDateFormat("dd MMMM yyyy | kk:mm", Locale("id", "ID"))
    return convert.format(this)
}

/** Result 31 January 2021 - 22:00 **/
fun Date.formatddMMMMyyyyhhmm2(): String? {
    val convert = SimpleDateFormat("dd MMMM yyyy - kk:mm", Locale("id", "ID"))
    return convert.format(this)
}

/** Result 31 Jan ‘21 **/
fun Date.formatddMMMyy(): String? {
    val convert = SimpleDateFormat("dd MMM ‘yy", Locale("id", "ID"))
    return convert.format(this)
}

/** Result 31 January 2021 | 18:30 WIB **/
fun Date.formatddMMMMyyyyhhmmz(): String {
    val convert = SimpleDateFormat("dd MMMM yyyy | kk:mm z", Locale("id", "ID"))
    return convert.format(this)
}

/** Result Sun, January 31 2021 **/
fun Date.formatEEEMMMMddyyyy(): String? {
    val convert = SimpleDateFormat("EEE, MMMM dd yyyy", Locale("id", "ID"))
    return convert.format(this)
}

/** Result 12:00 AM **/
fun Date.formathhmma(): String? {
    val convert = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
    return convert.format(this)
}

/** Result 2021-01-31 **/
fun Date.formatyyyyMMdd(): String? {
    val convert = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return convert.format(this)
}

/** Result 2021-01-31 13:30:30 **/
fun Date.formatyyyyMMddhhmmss(): String? {
    val convert = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
    return convert.format(this)
}

/** Result Sun 32 Jan 2021 **/
fun Date.formatEEEddMMMMyyyy(): String? {
    val convert = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault())
    return convert.format(this)
}

fun Date.toCalendar(): Calendar = Calendar.getInstance().apply { time = this@toCalendar }

fun Date.isBetween(start: Date, end: Date) = this.after(start) && this.before(end)

fun Date.isToday() = DateUtils.isToday(this.time)

fun Calendar.isSameDate(cal: Calendar) = this.get(Calendar.YEAR) == cal.get(Calendar.YEAR) &&
        this.get(Calendar.MONTH) == cal.get(Calendar.MONTH) &&
        this.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH)
