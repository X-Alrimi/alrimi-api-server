package com.ssu.capstone.alrimi.core.util.common

import kotlin.math.sqrt

object SimilarityUtil {

    val ALARM_SIMILARITY_LEVEL = 0.5
    fun calculateSimilarity(recentAlarm: List<Double>, critical: List<Double>): Double {
        var C: Double = 0.0
        var D: Double = 0.0
        var E: Double = 0.0
        for (index in recentAlarm.indices) {
            C += recentAlarm[index] * recentAlarm[index]
            D += critical[index] * critical[index]
            E += recentAlarm[index] * critical[index]
        }
        C = sqrt(C)
        D = sqrt(D)
        E = sqrt(E)

        print(E/(C*D))
        return E / (C * D)
    }
}