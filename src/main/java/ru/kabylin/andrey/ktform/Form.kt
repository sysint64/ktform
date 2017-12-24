package ru.kabylin.andrey.ktform

import android.content.Context
import android.view.ViewGroup
import ru.kabylin.andrey.ktform.fields.Field

open class Form(val context: Context, val container: ViewGroup? = null) {
    private val fields: MutableList<Field> = ArrayList()

    fun addField(field: Field) {
        if (field in fields)
            return

        fields.add(field)
        field.form = this
    }

    fun inflate(parent: ViewGroup) {
        fields.forEach { it.inflate(parent) }
    }

    fun isValid(): Boolean {
        var result = true

        for (field in fields) {
            val fieldIsValid = field.isValid()
            result = result && fieldIsValid
        }

        return result
    }

    fun setErrorForField(field: String, error: String) {
        fields
            .filter { it.name == field }
            .map { it.setError(error) }
    }
}

// DSL
fun Context.form(container: ViewGroup, init: (Form).() -> Unit): Form {
    val form = Form(this, container)
    form.init()
    return form
}

fun Context.form(init: (Form).() -> Unit): Form {
    val form = Form(this)
    form.init()
    return form
}
