package ru.kabylin.andrey.ktform.validators

import android.content.Context
import android.support.annotation.StringRes

typealias Hook = (value: Any?) -> Pair<Boolean, Boolean>
typealias ErrorTextHook = (value: Any?) -> Pair<Boolean, String>

abstract class Validator {
    private val hooks: MutableList<Hook> = ArrayList()
    private val errorTextsHooks: MutableList<ErrorTextHook> = ArrayList()
    lateinit var context: Context

    fun errorFromRes(@StringRes res: Int): String {
        return context.resources.getString(res)
    }

    fun addHook(hook: Hook): Validator {
        hooks.add(hook)
        return this
    }

    fun addErrorTextHook(hook: ErrorTextHook): Validator {
        errorTextsHooks.add(hook)
        return this
    }

    fun isHooksValid(value: Any?): Pair<Boolean, Boolean> {
        for (hook in hooks) {
            val (isHookType, isValid) = hook(value)
            if (isHookType)
                return Pair(isHookType, isValid)
        }

        return Pair(false, false)
    }

    fun isErrorTextHook(value: Any?): Pair<Boolean, String> {
        for (hook in errorTextsHooks) {
            val (isHookType, errorStringRes) = hook(value)
            if (isHookType)
                return Pair(isHookType, errorStringRes)
        }

        return Pair(false, "")
    }

    abstract fun isValid(value: Any?): Boolean
    abstract fun getErrorText(value: Any?): String
}
