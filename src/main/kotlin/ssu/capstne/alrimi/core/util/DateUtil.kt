package ssu.capstne.alrimi.core.util

import java.util.*

object DateUtil {
    private var calendar: Calendar = Calendar.getInstance()

    fun getWeekDaysBeforeDate(): Date {
        calendar.time = Date()
        calendar.set(Calendar.DATE, -7)

        return calendar.time
    }
}