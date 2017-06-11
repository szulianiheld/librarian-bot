package org.chechtalks.lunchbot.extensions

import org.chechtalks.lunchbot.bot.commands.Keyword
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StringsTest {

    val lowerCase = "this is a user message"
    val mixedCase = "ThiS Is A usEr meSsage"

    @Test
    fun contains_strings() {
        assertTrue { lowerCase.contains("this") }
        assertFalse { lowerCase.contains("messageee") }

        assertTrue { lowerCase.contains("this", "is", " a", "user", "message") }
        assertFalse { lowerCase.contains("this", "is", " a", "user", "message", "crap") }

        assertTrue { lowerCase.contains("thiS", "Is", " A", "usEr", "Message") }

        assertTrue { mixedCase.contains("thiS", "Is", " A", "usEr", "Message") }
    }

    @Test
    fun contains_keywords() {
        val keywordOk = Keyword("this", "esta")
        val keywordNotOk = Keyword("notpresent")

        assertTrue { lowerCase.contains(keywordOk) }
        assertFalse { lowerCase.contains(keywordNotOk) }
        assertFalse { lowerCase.contains(keywordOk, keywordNotOk) }
    }
}
