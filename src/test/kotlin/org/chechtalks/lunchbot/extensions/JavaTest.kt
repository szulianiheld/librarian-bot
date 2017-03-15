package org.chechtalks.lunchbot.extensions

import org.hamcrest.Matchers.*
import org.junit.Assert.assertThat
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class JavaTest {

    @Test
    fun toLocalDate() {

    }

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

    @Test
    fun firstContaining() {
        val messages = listOf("Message aaaa", "Message bdd", "Message bbddh")

        assertThat(messages.firstContaining("bd"), `is`(messages[1]))
        assertThat(messages.firstContaining("ge a"), `is`(messages[0]))
        assertThat(messages.firstContaining("Ma"), isEmptyOrNullString())

        assertThat(messages.firstContaining("AA"), `is`(messages[0]))
        assertThat(messages.firstContaining("AA", ignoreCase = false), nullValue())
    }

    @Test
    fun prettyToString(){
        assertThat(listOf("Message 1", "Message 2").prettyToString(), `is`("```Message 1```\n```Message 2```"))
        assertThat(listOf("Message 1").prettyToString(), `is`("```Message 1```"))
        assertThat(listOf("").prettyToString(), `is`("``````"))
    }

}
