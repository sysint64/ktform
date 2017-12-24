package ru.kabylin.andrey.ktform.validators

import android.content.res.Resources
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import ru.kabylin.andrey.ktform.R

class PhoneValidator(resources: Resources? = null): Validator(resources) {
    override fun getErrorText(value: Any?): String =
        errorFromRes(R.string.phone_validation_error)

    override fun isValid(value: Any?): Boolean {
        val phoneUtil = PhoneNumberUtil.getInstance()

        return when (value) {
            is CharSequence -> {
                try {
                    val numberProto = phoneUtil.parse(value.toString(), null)
                    phoneUtil.isValidNumber(numberProto)
                } catch (e: NumberParseException) {
                    false
                }
            }
            else -> throw UnsupportedOperationException()
        }
    }
}
