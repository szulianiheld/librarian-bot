package org.chechtalks.lunchbot


import me.ramswaroop.jbot.core.slack.SlackService
import me.ramswaroop.jbot.core.slack.models.User
import org.hamcrest.Matchers.containsString
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.runners.MockitoJUnitRunner
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.rule.OutputCapture
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import java.util.*

@RunWith(MockitoJUnitRunner::class)
@SpringBootTest
class LunchBotTest {

    @Mock
    lateinit private var session: WebSocketSession

    @Mock
    lateinit private var slackService: SlackService

    @InjectMocks
    lateinit private var bot: LunchBot

    @Rule @JvmField
    var capture = OutputCapture()

    @Before
    fun init() {
        val user = User()
        user.name = "LunchBot"
        user.id = "UEADGH12S"

        `when`(slackService.dmChannels).thenReturn(Arrays.asList("D1E79BACV", "C0NDSV5B8"))
        `when`(slackService.currentUser).thenReturn(user)
        `when`(slackService.webSocketUrl).thenReturn("")
    }

    @Test
    fun given_an_unrecognized_message_should_reply_with_default_response() {
        val textMessage = mockMessage("unrecognized command")
        bot.handleTextMessage(session, textMessage)
        assertThat(capture.toString(), containsString("No entendí"))
    }

    @Test
    fun given_a_cocina_soho_menu_request_should_reply_with_menu_info() {
        val textMessage = mockMessage("cual es el menu de cocina Soho?")
        bot.handleTextMessage(session, textMessage)
        assertThat(capture.toString(), containsString("me preguntó el menu en Soho"))
    }

    private fun mockMessage(message: String): TextMessage {
        return TextMessage("""
        {
            "type": "message",
            "ts": "1358878749.000002",
            "user": "U023BECGF",
            "text": "<@UEADGH12S>: $message"
        }
        """)
    }
}
