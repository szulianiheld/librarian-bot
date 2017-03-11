package org.chechtalks.lunchbot.bot


import me.ramswaroop.jbot.core.slack.Bot
import me.ramswaroop.jbot.core.slack.Controller
import me.ramswaroop.jbot.core.slack.EventType
import me.ramswaroop.jbot.core.slack.SlackService
import me.ramswaroop.jbot.core.slack.models.Event
import me.ramswaroop.jbot.core.slack.models.User
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.rule.OutputCapture
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession

import java.util.Arrays
import java.util.regex.Matcher

import org.hamcrest.Matchers.containsString
import org.junit.Assert.assertThat
import org.mockito.Mockito.`when`

@RunWith(MockitoJUnitRunner::class)
@SpringBootTest
class LunchBotTest {

    @Mock
    private val session: WebSocketSession? = null

    @Mock
    private val slackService: SlackService? = null

    @InjectMocks
    private val bot: TestBot? = null

    @Rule @JvmField
    var capture = OutputCapture()

    @Before
    fun init() {
        // set user
        val user = User()
        user.name = "LunchBot"
        user.id = "UEADGH12S"
        // set rtm
        `when`(slackService!!.dmChannels).thenReturn(Arrays.asList("D1E79BACV", "C0NDSV5B8"))
        `when`(slackService.currentUser).thenReturn(user)
        `when`(slackService.webSocketUrl).thenReturn("")
    }

    @Test
    @Throws(Exception::class)
    fun when_DirectMention_Should_InvokeOnDirectMention() {
        val textMessage = TextMessage("{\"type\": \"message\"," +
                "\"ts\": \"1358878749.000002\"," +
                "\"user\": \"U023BECGF\"," +
                "\"text\": \"<@UEADGH12S>: Hello\"}")
        bot!!.handleTextMessage(session, textMessage)
        assertThat(capture.toString(), containsString("Hi, I am"))
    }

    @Test
    @Throws(Exception::class)
    fun when_DirectMessage_Should_InvokeOnDirectMessage() {
        val textMessage = TextMessage("{\"type\": \"message\"," +
                "\"ts\": \"1358878749.000002\"," +
                "\"channel\": \"D1E79BACV\"," +
                "\"user\": \"U023BECGF\"," +
                "\"text\": \"Hello\"}")
        bot!!.handleTextMessage(session, textMessage)
        assertThat(capture.toString(), containsString("this is a direct message"))
    }

    @Test
    @Throws(Exception::class)
    fun when_MessageWithPattern_Should_InvokeOnReceiveMessageWithPattern() {
        val textMessage = TextMessage("{\"type\": \"message\"," +
                "\"ts\": \"1358878749.000002\"," +
                "\"channel\": \"A1E78BACV\"," +
                "\"user\": \"U023BECGF\"," +
                "\"text\": \"as12sd\"}")
        bot!!.handleTextMessage(session, textMessage)
        assertThat(capture.toString(), containsString("First group: as12sd"))
        assertThat(capture.toString(), containsString("Second group: as"))
        assertThat(capture.toString(), containsString("Third group: 12"))
        assertThat(capture.toString(), containsString("Fourth group: sd"))
    }

    @Test
    @Throws(Exception::class)
    fun when_DirectMessageWithPattern_Should_InvokeOnDirectMessage() {
        val textMessage = TextMessage("{\"type\": \"message\"," +
                "\"ts\": \"1358878749.000002\"," +
                "\"channel\": \"D1E79BACV\"," +
                "\"user\": \"U023BECGF\"," +
                "\"text\": \"as12sd\"}") // this matches the pattern but it's a direct message instead of message
        bot!!.handleTextMessage(session, textMessage)
        assertThat(capture.toString(), containsString("this is a direct message"))
    }

    @Test
    @Throws(Exception::class)
    fun when_DirectMessage_Should_InvokeOnPinAdded() {
        val textMessage = TextMessage("{\"type\":\"pin_added\"," +
                "\"user\":\"U0MCAEX8A\",\"channel_id\":\"C0NDSV5B8\"," +
                "\"item\":{\"type\":\"message\",\"channel\":\"C0NDSV5B8\"," +
                "\"message\":{\"type\":\"message\",\"user\":\"U1G0EBU2G\"," +
                "\"text\":\"Hi, I am nexagebot\",\"ts\":\"1471213096.000004\"," +
                "\"permalink\":\"https://aol.slack.com/archives/house-hunting/p1471213096000004\"," +
                "\"pinned_to\":[\"C0NDSV5B8\"]}," +
                "\"created\":1471213126}," +
                "\"item_user\":\"U1G0EBU2G\"," +
                "\"event_ts\":\"1471213126.954738\"}")
        bot!!.handleTextMessage(session, textMessage)
        assertThat(capture.toString(), containsString("Thanks for the pin"))
    }

    @Test
    @Throws(Exception::class)
    fun when_DirectMessage_Should_InvokeOnFileShared() {
        val textMessage = TextMessage("{\"type\":\"file_shared\"," +
                "\"file_id\":\"F219AF6VD\"," +
                "\"user_id\":\"U0MCAEX8A\"," +
                "\"file\":{\"id\":\"F219AF6VD\"}," +
                "\"event_ts\":\"1471213812.962298\"}")
        bot!!.handleTextMessage(session, textMessage)
        assertThat(capture.toString(), containsString("File shared"))
    }

    @Test
    @Throws(Exception::class)
    fun when_ConversationPattern_Should_StartConversation() {
        var textMessage = TextMessage("{\"type\": \"message\"," +
                "\"ts\": \"1158878749.000002\"," +
                "\"channel\": \"A1E78BACV\"," +
                "\"user\": \"U023BECGF\"," +
                "\"text\": \"setup meeting\"}")
        bot!!.handleTextMessage(session, textMessage)
        assertThat(capture.toString(), containsString("At what time (ex. 15:30) do you want me to set up the meeting?"))

        textMessage = TextMessage("{\"type\": \"message\"," +
                "\"ts\": \"1258878749.000002\"," +
                "\"channel\": \"A1E78BACV\"," +
                "\"user\": \"U023BECGF\"," +
                "\"text\": \"12:50\"}")
        bot.handleTextMessage(session, textMessage)
        assertThat(capture.toString(), containsString("Would you like to repeat it tomorrow?"))

        textMessage = TextMessage("{\"type\": \"message\"," +
                "\"ts\": \"1358878749.000002\"," +
                "\"channel\": \"A1E78BACV\"," +
                "\"user\": \"U023BECGF\"," +
                "\"text\": \"yes\"}")
        bot.handleTextMessage(session, textMessage)
        assertThat(capture.toString(), containsString("Would you like me to set a reminder for you"))

        textMessage = TextMessage("{\"type\": \"message\"," +
                "\"ts\": \"1458878749.000002\"," +
                "\"channel\": \"A1E78BACV\"," +
                "\"user\": \"U023BECGF\"," +
                "\"text\": \"yes\"}")
        bot.handleTextMessage(session, textMessage)
        assertThat(capture.toString(), containsString("I will remind you tomorrow before the meeting"))
    }

    @Test
    @Throws(Exception::class)
    fun given_InConversation_when_AnswerNo_Should_StopConversation() {
        var textMessage = TextMessage("{\"type\": \"message\"," +
                "\"ts\": \"1368878749.000602\"," +
                "\"channel\": \"A1E78BACV\"," +
                "\"user\": \"U023BECGF\"," +
                "\"text\": \"setup meeting\"}")
        bot!!.handleTextMessage(session, textMessage)
        assertThat(capture.toString(), containsString("At what time (ex. 15:30) do you want me to set up the meeting?"))

        textMessage = TextMessage("{\"type\": \"message\"," +
                "\"ts\": \"1348878749.000302\"," +
                "\"channel\": \"A1E78BACV\"," +
                "\"user\": \"U023BECGF\"," +
                "\"text\": \"12:40\"}")
        bot.handleTextMessage(session, textMessage)
        assertThat(capture.toString(), containsString("Would you like to repeat it tomorrow?"))

        textMessage = TextMessage("{\"type\": \"message\"," +
                "\"ts\": \"1358878749.000002\"," +
                "\"channel\": \"A1E78BACV\"," +
                "\"user\": \"U023BECGF\"," +
                "\"text\": \"no\"}")
        bot.handleTextMessage(session, textMessage)
        assertThat(capture.toString(), containsString("You can always schedule one with 'setup meeting' command"))
    }


    /**
     * Slack Bot for unit tests.
     */
    class TestBot : Bot() {

        override fun getSlackToken(): String {
            return "slackToken"
        }

        override fun getSlackBot(): Bot {
            return this
        }

        @Controller(events = arrayOf(EventType.DIRECT_MENTION))
        fun onDirectMention(session: WebSocketSession, event: Event) {
            println("Hi, I am LunchBot")
        }

        @Controller(events = arrayOf(EventType.DIRECT_MESSAGE))
        fun onDirectMessage(session: WebSocketSession, event: Event) {
            println("Hi, this is a direct message.")
        }

        @Controller(events = arrayOf(EventType.MESSAGE), pattern = "^([a-z ]{2})(\\d+)([a-z ]{2})$")
        fun onReceiveMessageWithPattern(session: WebSocketSession, event: Event, matcher: Matcher) {
            println("First group: " + matcher.group(0) + "\n" +
                    "Second group: " + matcher.group(1) + "\n" +
                    "Third group: " + matcher.group(2) + "\n" +
                    "Fourth group: " + matcher.group(3))
        }

        @Controller(events = arrayOf(EventType.PIN_ADDED))
        fun onPinAdded(session: WebSocketSession, event: Event) {
            println("Thanks for the pin! You can find all pinned items under channel details.")
        }

        @Controller(events = arrayOf(EventType.FILE_SHARED))
        fun onFileShared(session: WebSocketSession, event: Event) {
            println("File shared.")
        }

        /**
         * Conversation feature of JBot.
         */

        @Controller(pattern = "(setup meeting)", next = "confirmTiming")
        fun setupMeeting(session: WebSocketSession, event: Event) {
            startConversation(event, "confirmTiming")   // start conversation
            println("Cool! At what time (ex. 15:30) do you want me to set up the meeting?")
        }

        @Controller(next = "askTimeForMeeting")
        fun confirmTiming(session: WebSocketSession, event: Event) {
            println("Your meeting is set at " + event.text +
                    ". Would you like to repeat it tomorrow?")
            nextConversation(event)    // jump to next question in conversation
        }

        @Controller(next = "askWhetherToRepeat")
        fun askTimeForMeeting(session: WebSocketSession, event: Event) {
            if (event.text.contains("yes")) {
                println("Okay. Would you like me to set a reminder for you?")
                nextConversation(event)    // jump to next question in conversation
            } else {
                println("No problem. You can always schedule one with 'setup meeting' command.")
                stopConversation(event)    // stop conversation only if user says no
            }
        }

        @Controller
        fun askWhetherToRepeat(session: WebSocketSession, event: Event) {
            if (event.text.contains("yes")) {
                println("Great! I will remind you tomorrow before the meeting.")
            } else {
                println("Oh! my boss is smart enough to remind himself :)")
            }
            stopConversation(event)    // stop conversation
        }
    }
}
