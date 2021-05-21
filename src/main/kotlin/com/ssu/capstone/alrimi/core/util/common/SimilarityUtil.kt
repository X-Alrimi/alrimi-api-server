package com.ssu.capstone.alrimi.core.util.common

object SimilarityUtil {
    fun calculateSimilarity(recentAlarm: List<Double>, critical: List<Double>): Double {
        var sum: Double = 0.0
        for (index in recentAlarm.indices)
            sum += recentAlarm[index] * critical[index]

        return sum
    }
}