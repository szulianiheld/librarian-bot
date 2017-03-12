package org.chechtalks.lunchbot

import org.chechtalks.lunchbot.messaging.Message
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class MessageTest {

    @Test
    fun a_message_singlearg() {
        val message = Message("this is a user message")

        assertTrue { message.contains("this") }
        assertTrue { message.contains("is ") }
        assertTrue { message.contains(" a") }
        assertTrue { message.contains("user") }
        assertTrue { message.contains("message") }

        assertFalse { message.contains("messageee") }
    }

    @Test
    fun a_message_varargs() {
        val message = Message("this is a user message")

        assertTrue { message.contains("this", "is", " a", "user", "message") }
        assertFalse { message.contains("this", "is", " a", "user", "message", "crap") }
    }

    @Test
    fun a_message_lowercase_case_unsensitive() {
        val message = Message("this is a user message")

        assertTrue { message.contains("thiS", "Is", " A", "usEr", "Message") }
        assertFalse { message.contains("this", "is", " a", "user", "message", "crap") }
    }

    @Test
    fun a_message_mixed_case_unsensitive() {
        val message = Message("ThiS Is A usEr meSsage")

        assertTrue { message.contains("thiS", "Is", " A", "usEr", "Message") }
        assertFalse { message.contains("this", "is", " a", "user", "message", "crap") }
    }
}
