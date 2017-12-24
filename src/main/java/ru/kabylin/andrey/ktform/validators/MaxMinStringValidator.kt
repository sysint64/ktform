package ru.kabylin.andrey.ktform.validators

import ru.kabylin.andrey.ktform.R

class MaxMinStringValidator(val min: Int, val max: Int): Validator() {
    override fun isValid(value: Any?): Boolean {
        if (value !is String)
            throw UnsupportedOperationException()

        return value.length in min..max
    }

    override fun getErrorText(value: Any?): String {
        if (value !is CharSequence)
            throw UnsupportedOperationException()

        return when {
            value.length < min -> {
                val format = context.resources.getString(R.string.min_error)
                String.format(format, min)
            }
            value.length > max -> {
                val format = context.resources.getString(R.string.max_error)
                String.format(format, max)
            }
            else -> throw UnsupportedOperationException()
        }
    }
}
