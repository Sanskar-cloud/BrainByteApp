package dev.sanskar.featuretesteduco.core.util

import androidx.annotation.StringRes
import dev.sanskar.featuretesteduco.R.string

sealed class UiText {
    data class DynamicString(val value: String): UiText()
    data class StringResource(@StringRes val id: Int): UiText()

    companion object {
        fun unknownError(): UiText {
            return UiText.StringResource(string.error_unknown)
        }
    }
}