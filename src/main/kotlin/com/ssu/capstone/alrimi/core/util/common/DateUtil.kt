package com.ssu.capstone.alrimi.core.util.common

import java.util.*

object DateUtil {
    private var calendar: Calendar = Calendar.getInstance()

    fun getWeekDaysBeforeDate(): Date {
        calendar.time = Date()
        calendar.set(Calendar.DATE, -7)

        return calendar.time
    }
}