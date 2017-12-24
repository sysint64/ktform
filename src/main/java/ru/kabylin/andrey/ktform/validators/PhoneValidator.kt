package ru.kabylin.andrey.ktform.validators

import com.google.i18n.phonenumbers.PhoneNumberUtil
import ru.kabylin.andrey.ktform.R

class PhoneValidator: Validator() {
    override fun getErrorText(value: Any?): String =
        context.resources.getString(R.string.email_validation_error)

    override fun isValid(value: Any?): Boolean {
        val phoneUtil = PhoneNumberUtil.getInstance()

        return when (value) {
            is CharSequence -> {
                val numberProto = phoneUtil.parse(value.toString(), null)
                phoneUtil.isValidNumber(numberProto)
            }
            else -> throw UnsupportedOperationException()
        }
    }
}
