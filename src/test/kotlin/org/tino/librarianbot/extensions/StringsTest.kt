package org.tino.librarianbot.extensions

import org.tino.librarianbot.bot.commands.Keyword
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StringsTest {

    private val lowerCase = "this is a user message"

    @Test
    fun contains_keywords() {
        val keywordOk = Keyword("this", "esta")
        val keywordNotOk = Keyword("notpresent")

        assertTrue { lowerCase.contains(keywordOk) }
        assertFalse { lowerCase.contains(keywordNotOk) }
        assertFalse { lowerCase.contains(keywordOk, keywordNotOk) }
    }
}
