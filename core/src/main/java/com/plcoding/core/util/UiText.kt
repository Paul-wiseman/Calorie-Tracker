package com.plcoding.core.util

import android.content.Context

sealed class UiText {
    data class DynamicString(val input: String) : UiText()
    data class StringResource(val resId: Int) : UiText()

    fun uiTextToString(context: Context) = when (this) {
        is DynamicString -> this.input
        is StringResource -> context.getString(this.resId)
    }

}