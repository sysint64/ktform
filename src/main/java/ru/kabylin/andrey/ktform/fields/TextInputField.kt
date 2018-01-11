package ru.kabylin.andrey.ktform.fields

import android.support.design.widget.TextInputLayout
import android.view.ViewGroup
import android.widget.EditText
import ru.kabylin.andrey.ktform.Form
import ru.kabylin.andrey.ktform.validators.Validator

open class TextInputField(name: String, val container: ViewGroup? = null) : Field(name) {
    protected lateinit var editText: EditText
    protected lateinit var textInputLayout: TextInputLayout

    var hint: String = ""
        set(value) {
            textInputLayout.hint = value
            field = value
        }

    override fun getValue(): String =
        editText.text.toString()

    fun attach(editText: EditText, textInputLayout: TextInputLayout) {
        this.editText = editText
        this.textInputLayout = textInputLayout
    }

    override fun inflate(parent: ViewGroup) {
//        val field = this@TextInputField
//
//        this.textInputLayout = parent.textInputLayout {
//            field.editText = editText {
//                hint = field.hint
//            }
//        }
    }

    override fun setError(error: String) {
        textInputLayout.error = error
    }

    override fun clearError() {
        textInputLayout.error = null
    }

    fun editTextAttrs(init: (EditText).() -> Unit) {
        editText.init()
    }

    fun textInputLayoutAttrs(init: (TextInputLayout).() -> Unit) {
        textInputLayout.init()
    }

    override var isEnabled: Boolean
        get() = textInputLayout.isEnabled
        set(value) {
            textInputLayout.isEnabled = value
            editText.isEnabled = value
        }
}

// DSL
fun Form.editText(name: String): TextInputField {
    val field = TextInputField(name, this.container)
    addField(field)
    return field
}

fun Form.editText(name: String, validators: List<Validator>): TextInputField {
    val field = editText(name)
    field.validators = validators
    return field
}

fun Form.editText(name: String, validators: List<Validator>, init: (TextInputField).() -> Unit): TextInputField {
    val field = editText(name, validators)
    field.init()
    return field
}

fun Form.editText(name: String, init: (TextInputField).() -> Unit): TextInputField {
    val field = editText(name)
    field.init()
    return field
}
