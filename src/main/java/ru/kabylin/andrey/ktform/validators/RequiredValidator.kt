package ru.kabylin.andrey.ktform.validators

import ru.kabylin.andrey.ktform.R

class RequiredValidator : Validator() {
    override fun getErrorText(value: Any?): String {
        return errorFromRes(R.string.required_error)
    }

    override fun isValid(value: Any?): Boolean =
        when (value) {
            value == null -> false
            is String -> !value.isBlank()
            is Boolean -> value
            else -> throw UnsupportedOperationException()
        }
}
