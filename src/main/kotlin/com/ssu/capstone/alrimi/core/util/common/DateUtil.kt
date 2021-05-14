package com.ssu.capstone.alrimi.core.util.common

import java.util.*

object DateUtil {
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
        if (date == null)
            return true
        else {
            calendar.time = date
            calendar.set(Calendar.DATE, 1)

            return calendar.before(Date())
        }
    }
}