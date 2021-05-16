package com.ssu.capstone.alrimi.core.util.common

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    private val format = SimpleDateFormat("yyyy-MM-dd-HH:mm")
    private var calendar: Calendar = Calendar.getInstance()

    fun getWeekDaysBeforeDate(): Date {
        calendar.time = Date()
        calendar.set(Calendar.DATE, -7)

        return calendar.time
    }

    /**
     * 최근 알람이 하루 이내이면 알람 발생 x
     */
    fun canAlarm(date: Date?): Boolean {
        return if (date == null)
            true
        else {
            calendar.time = date
            calendar.add(Calendar.DATE, 1)
            return calendar.time.before(Date())
        }
    }

    fun getStringFromDate(date: Date): String {
        return format.format(date)
    }

    fun getDateFromString(date: String): Date {
        return format.parse(date)
    }
}