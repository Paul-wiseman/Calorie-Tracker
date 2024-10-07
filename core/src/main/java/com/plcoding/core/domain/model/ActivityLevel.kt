package com.plcoding.core.domain.model

sealed class ActivityLevel(val name: String) {
    object Low: ActivityLevel("low")
    object Medium : ActivityLevel("medium")
    object High : ActivityLevel("high")

    companion object {
        fun fromString(goalType: String): ActivityLevel {
            return when (goalType) {
                "low" -> Low
                "medium" -> Medium
                "high" -> High
                else -> Medium
            }
        }
    }
}