package org.chechtalks.lunchbot.extensions

import org.hamcrest.Matchers.*
import org.junit.Assert.assertThat
import org.junit.Test

class ListsTest {

    private val messages = listOf("Message aaaa", "Message bdd", "Message bbddh")

    @Test
    fun firstContaining() {
        assertThat(messages.firstContaining("bd"), `is`(messages[1]))
        assertThat(messages.firstContaining("ge a"), `is`(messages[0]))
        assertThat(messages.firstContaining("Ma"), isEmptyOrNullString())

        assertThat(messages.firstContaining("AA"), `is`(messages[0]))
        assertThat(messages.firstContaining("AA", ignoreCase = false), nullValue())
    }

    @Test
    fun preformatted() {
        assertThat(listOf("").preformatted(), `is`(listOf("``````")))
        assertThat(emptyList<String>().preformatted(), `is`(emptyList()))

        val preformatted = messages.preformatted()
        assertThat(preformatted, hasSize(3))
        assertThat(preformatted, contains("```Message aaaa```", "```Message bdd```", "```Message bbddh```"))
    }

    @Test
    fun quoted() {
        assertThat(listOf("").quoted(), `is`(listOf("> ")))
        assertThat(emptyList<String>().quoted(), `is`(emptyList()))

        val quoted = messages.quoted()
        assertThat(quoted, hasSize(3))
        assertThat(quoted, contains("> Message aaaa", "> Message bdd", "> Message bbddh"))
    }

}
