package org.chechtalks.lunchbot.config

import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class MessageResolverTest() {

    @Autowired
    private lateinit var messages: MessageResolver

    @Test
    fun it_resolves_existent_messages() {
        assertThat(messages.get("lunchbot.response.default"), `is`("No Entendí"));
    }

    @Test
    fun it_resolves_nonexistent_messages() {
        assertThat(messages.get("totally.nonexistent.code"), `is`("totally.nonexistent.code"));
    }

}
