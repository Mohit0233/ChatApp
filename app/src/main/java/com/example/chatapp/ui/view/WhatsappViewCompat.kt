package com.example.chatapp.ui.view

import android.graphics.Color
import android.graphics.Typeface
import android.os.Handler
import android.text.*
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.widget.EditText
import android.widget.TextView
import java.util.*

object WhatsappViewCompat {
    fun applyFormatting(editText: EditText, vararg watchers: TextWatcher) {
        val mEditTextWatcher: TextWatcher = object : TextWatcher {
            val mainWatcher: TextWatcher = this
            var handler = Handler()
            private val formatRunnable = Runnable { format(editText, mainWatcher, watchers) }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                sendBeforeTextChanged(watchers, s, start, count, after)
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                sendOnTextChanged(watchers, s, start, before, count)
            }

            override fun afterTextChanged(s: Editable) {
                handler.removeCallbacks(formatRunnable)
                handler.postDelayed(formatRunnable, 220)
            }
        }
        val text = editText.text.toString()
        if (!TextUtils.isEmpty(text)) {
            val formatted = extractFlagsForEditText(text)
            editText.setText(formatted)
        }
        editText.addTextChangedListener(mEditTextWatcher)
    }

    private fun format(
        editText: EditText,
        mainWatcher: TextWatcher,
        otherWatchers: Array<out TextWatcher>
    ) {
        val text = editText.text
        val formatted = extractFlagsForEditText(text)
        removeTextChangedListener(editText, mainWatcher)
        val selectionEnd = editText.selectionEnd
        val selectionStart = editText.selectionStart
        editText.setText(formatted)
        editText.setSelection(selectionStart, selectionEnd)
        val formattedEditableText = editText.text
        sendAfterTextChanged(otherWatchers, formattedEditableText)
        addTextChangedListener(editText, mainWatcher)
    }

    fun applyFormatting(textView: TextView, vararg watchers: TextWatcher) {
        val mEditTextWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                sendBeforeTextChanged(watchers, s, start, count, after)
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                sendOnTextChanged(watchers, s, start, before, count)
            }

            override fun afterTextChanged(s: Editable) {
                val formatted = extractFlagsForTextView(s)
                removeTextChangedListener(textView, this)
                textView.setText(formatted, TextView.BufferType.EDITABLE)
                val formattedEditableText = textView.text as Editable
                sendAfterTextChanged(watchers, formattedEditableText)
                addTextChangedListener(textView, this)
            }
        }
        val text = textView.text.toString()
        if (!TextUtils.isEmpty(text)) {
            val formatted = extractFlagsForTextView(text)
            textView.text = formatted
        }
        textView.addTextChangedListener(mEditTextWatcher)
    }

    private fun sendAfterTextChanged(mListeners: Array<out TextWatcher>, s: Editable) {
        for (i in mListeners.indices) {
            mListeners[i].afterTextChanged(s)
        }
    }

    private fun sendOnTextChanged(
        mListeners: Array<out TextWatcher>,
        s: CharSequence,
        start: Int,
        before: Int,
        count: Int
    ) {
        for (i in mListeners.indices) {
            mListeners[i].onTextChanged(s, start, before, count)
        }
    }

    private fun sendBeforeTextChanged(
        mListeners: Array<out TextWatcher>,
        s: CharSequence,
        start: Int,
        count: Int,
        after: Int
    ) {
        for (i in mListeners.indices) {
            mListeners[i].beforeTextChanged(s, start, count, after)
        }
    }

    fun removeTextChangedListener(textView: TextView, watcher: TextWatcher?) {
        textView.removeTextChangedListener(watcher)
    }

    fun addTextChangedListener(textView: TextView, watcher: TextWatcher?) {
        textView.addTextChangedListener(watcher)
    }

    fun extractFlagsForTextView(text: CharSequence): CharSequence {
        val textChars = text.toString().toCharArray()
        val characters = ArrayList<Char>()
        val flags = ArrayList<WhatsappUtil.Flag>()
        var boldFlag = WhatsappUtil.Flag(
            WhatsappUtil.INVALID_INDEX,
            WhatsappUtil.INVALID_INDEX,
            WhatsappUtil.BOLD_FLAG
        )
        var strikeFlag = WhatsappUtil.Flag(
            WhatsappUtil.INVALID_INDEX,
            WhatsappUtil.INVALID_INDEX,
            WhatsappUtil.STRIKE_FLAG
        )
        var italicFlag = WhatsappUtil.Flag(
            WhatsappUtil.INVALID_INDEX,
            WhatsappUtil.INVALID_INDEX,
            WhatsappUtil.ITALIC_FLAG
        )
        run {
            var i = 0
            var j = 0
            while (i < textChars.size) {
                val c = textChars[i]
                if (c == WhatsappUtil.BOLD_FLAG) {
                    if (boldFlag.start == WhatsappUtil.INVALID_INDEX) {
                        if (WhatsappUtil.hasFlagSameLine(text, WhatsappUtil.BOLD_FLAG, i + 1)) {
                            boldFlag.start = j
                            i++
                            continue
                        }
                    } else {
                        boldFlag.end = j
                        flags.add(boldFlag)
                        boldFlag = WhatsappUtil.Flag(
                            WhatsappUtil.INVALID_INDEX,
                            WhatsappUtil.INVALID_INDEX,
                            WhatsappUtil.BOLD_FLAG
                        )
                        i++
                        continue
                    }
                }
                if (c == WhatsappUtil.STRIKE_FLAG) {
                    if (strikeFlag.start == WhatsappUtil.INVALID_INDEX) {
                        if (WhatsappUtil.hasFlagSameLine(text, WhatsappUtil.STRIKE_FLAG, i + 1)) {
                            strikeFlag.start = j
                            i++
                            continue
                        }
                    } else {
                        strikeFlag.end = j
                        flags.add(strikeFlag)
                        strikeFlag = WhatsappUtil.Flag(
                            WhatsappUtil.INVALID_INDEX,
                            WhatsappUtil.INVALID_INDEX,
                            WhatsappUtil.STRIKE_FLAG
                        )
                        i++
                        continue
                    }
                }
                if (c == WhatsappUtil.ITALIC_FLAG) {
                    if (italicFlag.start == WhatsappUtil.INVALID_INDEX) {
                        if (WhatsappUtil.hasFlagSameLine(text, WhatsappUtil.ITALIC_FLAG, i + 1)) {
                            italicFlag.start = j
                            i++
                            continue
                        }
                    } else {
                        italicFlag.end = j
                        flags.add(italicFlag)
                        italicFlag = WhatsappUtil.Flag(
                            WhatsappUtil.INVALID_INDEX,
                            WhatsappUtil.INVALID_INDEX,
                            WhatsappUtil.ITALIC_FLAG
                        )
                        i++
                        continue
                    }
                }
                characters.add(c)
                j++
                i++
            }
        }
        val formatted = WhatsappUtil.charArrayListToString(characters)
        val builder = SpannableStringBuilder(formatted)
        for (i in flags.indices) {
            val flag = flags[i]
            when (flag.flag) {
                WhatsappUtil.BOLD_FLAG -> {
                    val bss = StyleSpan(Typeface.BOLD)
                    builder.setSpan(bss, flag.start, flag.end, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
                }
                WhatsappUtil.STRIKE_FLAG -> {
                    builder.setSpan(
                        StrikethroughSpan(),
                        flag.start,
                        flag.end,
                        Spanned.SPAN_INCLUSIVE_INCLUSIVE
                    )
                }
                WhatsappUtil.ITALIC_FLAG -> {
                    val iss = StyleSpan(Typeface.ITALIC)
                    builder.setSpan(iss, flag.start, flag.end, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
                }
            }
        }
        return builder
    }

    fun extractFlagsForEditText(text: CharSequence): CharSequence {
        val textChars = text.toString().toCharArray()
        val characters = ArrayList<Char>()
        val flags = ArrayList<WhatsappUtil.Flag>()
        var boldFlag = WhatsappUtil.Flag(
            WhatsappUtil.INVALID_INDEX,
            WhatsappUtil.INVALID_INDEX,
            WhatsappUtil.BOLD_FLAG
        )
        var strikeFlag = WhatsappUtil.Flag(
            WhatsappUtil.INVALID_INDEX,
            WhatsappUtil.INVALID_INDEX,
            WhatsappUtil.STRIKE_FLAG
        )
        var italicFlag = WhatsappUtil.Flag(
            WhatsappUtil.INVALID_INDEX,
            WhatsappUtil.INVALID_INDEX,
            WhatsappUtil.ITALIC_FLAG
        )
        run {
            var i = 0
            var j = 0
            while (i < textChars.size) {
                val c = textChars[i]
                if (c == WhatsappUtil.BOLD_FLAG) {
                    if (boldFlag.start == WhatsappUtil.INVALID_INDEX) {
                        if (WhatsappUtil.hasFlagSameLine(text, WhatsappUtil.BOLD_FLAG, i + 1)) {
                            boldFlag.start = j + 1
                        }
                    } else {
                        boldFlag.end = j
                        flags.add(boldFlag)
                        boldFlag = WhatsappUtil.Flag(
                            WhatsappUtil.INVALID_INDEX,
                            WhatsappUtil.INVALID_INDEX,
                            WhatsappUtil.BOLD_FLAG
                        )
                    }
                } else if (c == WhatsappUtil.STRIKE_FLAG) {
                    if (strikeFlag.start == WhatsappUtil.INVALID_INDEX) {
                        if (WhatsappUtil.hasFlagSameLine(text, WhatsappUtil.STRIKE_FLAG, i + 1)) {
                            strikeFlag.start = j + 1
                        }
                    } else {
                        strikeFlag.end = j
                        flags.add(strikeFlag)
                        strikeFlag = WhatsappUtil.Flag(
                            WhatsappUtil.INVALID_INDEX,
                            WhatsappUtil.INVALID_INDEX,
                            WhatsappUtil.STRIKE_FLAG
                        )
                    }
                } else if (c == WhatsappUtil.ITALIC_FLAG) {
                    if (italicFlag.start == WhatsappUtil.INVALID_INDEX) {
                        if (WhatsappUtil.hasFlagSameLine(text, WhatsappUtil.ITALIC_FLAG, i + 1)) {
                            italicFlag.start = j + 1
                        }
                    } else {
                        italicFlag.end = j
                        flags.add(italicFlag)
                        italicFlag = WhatsappUtil.Flag(
                            WhatsappUtil.INVALID_INDEX,
                            WhatsappUtil.INVALID_INDEX,
                            WhatsappUtil.ITALIC_FLAG
                        )
                    }
                }
                characters.add(c)
                j++
                i++
            }
        }
        val formatted = WhatsappUtil.charArrayListToString(characters)
        val builder = SpannableStringBuilder(formatted)
        for (i in flags.indices) {
            val flag = flags[i]
            when (flag.flag) {
                WhatsappUtil.BOLD_FLAG -> {
                    val bss = StyleSpan(Typeface.BOLD)
                    builder.setSpan(bss, flag.start, flag.end, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
                    builder.setSpan(
                        ForegroundColorSpan(Color.GRAY),
                        flag.start - 1,
                        flag.start,
                        Spanned.SPAN_INCLUSIVE_INCLUSIVE
                    )
                    builder.setSpan(
                        ForegroundColorSpan(Color.GRAY),
                        flag.end,
                        flag.end + 1,
                        Spanned.SPAN_INCLUSIVE_INCLUSIVE
                    )
                }
                WhatsappUtil.STRIKE_FLAG -> {
                    builder.setSpan(
                        StrikethroughSpan(),
                        flag.start,
                        flag.end,
                        Spanned.SPAN_INCLUSIVE_INCLUSIVE
                    )
                    builder.setSpan(
                        ForegroundColorSpan(Color.GRAY),
                        flag.start - 1,
                        flag.start,
                        Spanned.SPAN_INCLUSIVE_INCLUSIVE
                    )
                    builder.setSpan(
                        ForegroundColorSpan(Color.GRAY),
                        flag.end,
                        flag.end + 1,
                        Spanned.SPAN_INCLUSIVE_INCLUSIVE
                    )
                }
                WhatsappUtil.ITALIC_FLAG -> {
                    val iss = StyleSpan(Typeface.ITALIC)
                    builder.setSpan(iss, flag.start, flag.end, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
                    builder.setSpan(
                        ForegroundColorSpan(Color.GRAY),
                        flag.start - 1,
                        flag.start,
                        Spanned.SPAN_INCLUSIVE_INCLUSIVE
                    )
                    builder.setSpan(
                        ForegroundColorSpan(Color.GRAY),
                        flag.end,
                        flag.end + 1,
                        Spanned.SPAN_INCLUSIVE_INCLUSIVE
                    )
                }
            }
        }
        return builder
    }
}