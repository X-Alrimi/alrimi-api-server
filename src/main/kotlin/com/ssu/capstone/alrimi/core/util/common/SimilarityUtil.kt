package com.ssu.capstone.alrimi.core.util.common

import kotlin.math.pow
import kotlin.math.sqrt

object SimilarityUtil {

    val ALARM_SIMILARITY_LEVEL = 0.5
    fun calculateSimilarity(recentAlarm: List<Double>, critical: List<Double>): Double {
        var C: Double = 0.0
        var D: Double = 0.0
        var E: Double = 0.0
        for (index in recentAlarm.indices) {
            C += recentAlarm[index].pow(2.0)
            D += critical[index] * critical[index]
            E += recentAlarm[index] * critical[index]
        }
        C = sqrt(C)
        D = sqrt(D)

        return E / (C * D)
    }
}