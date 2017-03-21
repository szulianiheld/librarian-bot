package org.chechtalks.lunchbot.extensions

import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StringsTest {

    @Test
    fun contains() {
        val lowerCase = "this is a user message"
        val mixedCase = "ThiS Is A usEr meSsage"

        assertTrue { lowerCase.contains("this") }
        assertFalse { lowerCase.contains("messageee") }

        assertTrue { lowerCase.contains("this", "is", " a", "user", "message") }
        assertFalse { lowerCase.contains("this", "is", " a", "user", "message", "crap") }

        assertTrue { lowerCase.contains("thiS", "Is", " A", "usEr", "Message") }

        assertTrue { mixedCase.contains("thiS", "Is", " A", "usEr", "Message") }
    }
}
