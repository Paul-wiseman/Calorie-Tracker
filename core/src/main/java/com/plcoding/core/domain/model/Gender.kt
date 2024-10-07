package com.plcoding.core.domain.model

sealed class Gender(val name: String) {
    object Male : Gender("male")
    object Female : Gender("female")

    companion object {
        fun fromString(gender: String): Gender {
            return when (gender) {
                "male" -> Male
                "female" -> Female
                else -> Male
            }
        }
    }
}