package com.plcoding.core.domain.model

sealed class GoalType(val name: String) {
    object LoseWeight: GoalType("lose_weight")
    object GainWeight : GoalType("gain_weight")
    object KeepWeight : GoalType("keep_weight")

    companion object {
        fun fromString(goalType: String): GoalType {
            return when (goalType) {
                "lose_weight" -> LoseWeight
                "gain_weight" -> GainWeight
                "keep_weight" -> KeepWeight
                else -> KeepWeight
            }
        }
    }
}