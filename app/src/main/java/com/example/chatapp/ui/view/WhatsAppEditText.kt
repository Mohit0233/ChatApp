package com.example.chatapp.ui.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import java.util.*

class WhatsAppEditText : AppCompatEditText {
    private var arrayListOfTextWatchers: ArrayList<TextWatcher>? = null

    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!, attrs, defStyleAttr
    ) {
        init()
    }

    private fun init() {
        addTextChangedListener(mEditTextWatcher)
    }

    private val mEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            sendBeforeTextChanged(s, start, count, after)
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            sendOnTextChanged(s, start, before, count)
        }

        override fun afterTextChanged(s: Editable) {
            postDelayed({ format() }, 10)
        }
    }

    private fun format() {
        val text = text!!
        val formatted = WhatsappViewCompat.extractFlagsForEditText(text)
        removeTextChangedListener(mEditTextWatcher)
        val selectionEnd = selectionEnd
        val selectionStart = selectionStart
        setText(formatted)
        setSelection(selectionStart, selectionEnd)
        val formattedEditableText = getText()
        sendAfterTextChanged(formattedEditableText)
        addTextChangedListener(mEditTextWatcher)
    }

    private fun sendBeforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        if (arrayListOfTextWatchers != null) {
            for (i in arrayListOfTextWatchers!!.indices) {
                arrayListOfTextWatchers!![i].beforeTextChanged(s, start, count, after)
            }
        }
    }

    private fun sendOnTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (arrayListOfTextWatchers != null) {
            for (i in arrayListOfTextWatchers!!.indices) {
                arrayListOfTextWatchers!![i].onTextChanged(s, start, before, count)
            }
        }
    }

    private fun sendAfterTextChanged(s: Editable?) {
        if (arrayListOfTextWatchers != null) {
            for (i in arrayListOfTextWatchers!!.indices) {
                arrayListOfTextWatchers!![i].afterTextChanged(s)
            }
        }
    }

    override fun addTextChangedListener(watcher: TextWatcher) {
        if (watcher !== mEditTextWatcher) {
            if (arrayListOfTextWatchers == null) {
                arrayListOfTextWatchers = ArrayList()
            }
            arrayListOfTextWatchers!!.add(watcher)
        } else {
            super.addTextChangedListener(watcher)
        }
    }

    override fun removeTextChangedListener(watcher: TextWatcher) {
        if (watcher !== mEditTextWatcher) {
            if (arrayListOfTextWatchers != null) {
                val i = arrayListOfTextWatchers!!.indexOf(watcher)
                if (i >= 0) {
                    arrayListOfTextWatchers!!.removeAt(i)
                }
            }
        } else {
            super.removeTextChangedListener(watcher)
        }
    }
}