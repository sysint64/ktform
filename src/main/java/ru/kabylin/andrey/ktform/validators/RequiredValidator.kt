package ru.kabylin.andrey.ktform.validators

import android.content.res.Resources
import ru.kabylin.andrey.ktform.R

class RequiredValidator(resources: Resources? = null) : Validator(resources) {
    override fun getErrorText(value: Any?): String =
        errorFromRes(R.string.required_error)

    override fun isValid(value: Any?): Boolean =
        when (value) {
            value == null -> false
            is String -> !value.isBlank()
            is Boolean -> value
            else -> throw UnsupportedOperationException()
        }
}
