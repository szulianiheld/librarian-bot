package org.tino.librarianbot.config

import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.containsSubstring
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.expect

@RunWith(SpringRunner::class)
@SpringBootTest
class MessageResolverTest {

    @Autowired
    private lateinit var messages: MessageResolver

    @Test
    fun `it resolves existent messages`() {
        assert.that(messages.get("lunchbot.response.default"), containsSubstring("No Entendí"))
    }

    @Test
    fun it_resolves_nonexistent_messages() {
        expect("totally.nonexistent.code") { messages.get("totally.nonexistent.code") }
    }

}
