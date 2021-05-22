package com.ssu.capstone.alrimi.core.util.common

object SimilarityUtil {

    val ALARM_SIMILARITY_LEVEL = 50.00
    fun calculateSimilarity(recentAlarm: List<Double>, critical: List<Double>): Double {
        var sum: Double = 0.0
        for (index in recentAlarm.indices)
            sum += recentAlarm[index] * critical[index]

        println(sum)
        return sum
    }
}