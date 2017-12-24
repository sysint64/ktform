package ru.kabylin.andrey.ktform.validators

import ru.kabylin.andrey.ktform.R

class EmailValidator: Validator() {
    override fun getErrorText(value: Any?): String =
        context.resources.getString(R.string.email_validation_error)

    override fun isValid(value: Any?): Boolean =
        when (value) {
            is CharSequence -> android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()
            else -> throw UnsupportedOperationException()
        }
}
