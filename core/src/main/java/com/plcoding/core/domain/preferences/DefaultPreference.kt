package com.plcoding.core.domain.preferences

import android.content.SharedPreferences
import com.plcoding.core.domain.model.ActivityLevel
import com.plcoding.core.domain.model.Gender
import com.plcoding.core.domain.model.GoalType
import com.plcoding.core.domain.model.UserInfo

class DefaultPreference(
    private val sharedPreferences: SharedPreferences
) : Preferences {
    override fun saveGender(gender: Gender) {
        edit { putString(KEY_GENDER, gender.name) }
    }

    override fun saveAge(age: Int) {
        edit { putInt(KEY_AGE, age) }

    }

    override fun saveWeight(weight: Float) {
        edit { putFloat(KEY_WEIGHT, weight) }
    }

    override fun saveHeight(height: Int) {
        edit { putInt(KEY_HEIGHT, height) }
    }

    override fun saveActivityLevel(level: ActivityLevel) {
        edit { putString(KEY_ACTIVITY_LEVEL, level.name) }
    }

    override fun saveGoalType(type: GoalType) {
        edit { putString(KEY_GOAL_TYPE, type.name) }
    }

    override fun saveCarbRatio(ratio: Float) {
        edit { putFloat(KEY_CARB_RATIO, ratio) }
    }

    override fun saveProteinRatio(ratio: Float) {
        edit { putFloat(KEY_PROTEIN_RATIO, ratio) }
    }

    override fun saveFatRatio(ratio: Float) {
        edit { putFloat(KEY_FAT_RATIO, ratio) }
    }

    override fun loadUserInfo(): UserInfo {
        val age = sharedPreferences.getInt(KEY_AGE, -1)
        val height = sharedPreferences.getFloat(KEY_HEIGHT, -1f)
        val weight = sharedPreferences.getFloat(KEY_WEIGHT, -1f)
        val gender = sharedPreferences.getString(KEY_GENDER, null)
        val activityLevel = sharedPreferences.getString(KEY_ACTIVITY_LEVEL, null)
        val goalType = sharedPreferences.getString(KEY_GOAL_TYPE, null)
        val carbRatio = sharedPreferences.getFloat(KEY_CARB_RATIO, -1f)
        val proteinRatio = sharedPreferences.getFloat(KEY_AGE, -1f)
        val fatRatio = sharedPreferences.getFloat(KEY_AGE, -1f)
        return UserInfo(
            gender = Gender.fromString(gender ?: "male"),
            age = age,
            weight = weight,
            height = height,
            activityLevel = ActivityLevel.fromString(activityLevel ?: "low"),
            goalType = GoalType.fromString(goalType ?: "keep_weight"),
            carbRatio = carbRatio,
            proteinRatio = proteinRatio,
            fatRatio = fatRatio

        )
    }

    private inline fun edit(block: SharedPreferences.Editor.() -> Unit) {
        with(sharedPreferences.edit()) {
            block()
            commit()
        }
    }

    private companion object {
        const val KEY_AGE = "age"
        const val KEY_GENDER = "gender"
        const val KEY_WEIGHT = "weight"
        const val KEY_HEIGHT = "height"
        const val KEY_ACTIVITY_LEVEL = "activity_level"
        const val KEY_GOAL_TYPE = "goal_type"
        const val KEY_CARB_RATIO = "carb_ratio"
        const val KEY_PROTEIN_RATIO = "protein_ratio"
        const val KEY_FAT_RATIO = "fat_ratio"
    }
}