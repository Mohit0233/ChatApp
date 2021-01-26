package com.example.chatapp.ui.view

import java.util.*

object WhatsappUtil {
    private const val NEW_LINE = '\n'
    private const val SPACE = ' '
    const val BOLD_FLAG = '*'
    const val STRIKE_FLAG = '~'
    const val ITALIC_FLAG = '_'
    const val INVALID_INDEX = -1

    fun isFlagged(text: CharSequence, index: Int): Boolean {
        if (index > INVALID_INDEX && index < text.length) {
            val c = text[index]
            return c == SPACE || c == NEW_LINE || c == BOLD_FLAG || c == ITALIC_FLAG || c == STRIKE_FLAG
        }
        return true
    }

    fun charArrayListToString(characters: ArrayList<Char>): String {
        // val str: String = characters.stream().map { e -> e.toString() }.collect(Collectors.joining())
        val chars = CharArray(characters.size)
        for (i in chars.indices) {
            chars[i] = characters[i]
        }
        return String(chars)
    }

    fun hasFlagSameLine(sequence: CharSequence, flag: Char, fromIndex: Int): Boolean {
        for (i in fromIndex until sequence.length) {
            val c = sequence[i]
            if (c == NEW_LINE) return false
            if (c == flag) return i != fromIndex
        }
        return false
    }

    class Flag(var start: Int, var end: Int, var flag: Char)
}