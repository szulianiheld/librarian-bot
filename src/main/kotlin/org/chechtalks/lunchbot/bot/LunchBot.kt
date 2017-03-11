package org.chechtalks.lunchbot.bot

import me.ramswaroop.jbot.core.slack.Bot
import me.ramswaroop.jbot.core.slack.Controller
import me.ramswaroop.jbot.core.slack.EventType
import me.ramswaroop.jbot.core.slack.models.Event
import me.ramswaroop.jbot.core.slack.models.Message
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.socket.WebSocketSession

import java.util.regex.Matcher

@Component
class LunchBot : Bot() {

    val LOG = LoggerFactory.getLogger(LunchBot::class.java)

    @Value("\${slackBotToken}")
    lateinit private var slackToken: String

    override fun getSlackToken(): String {
        LOG.trace("getting slack token : $slackToken")
        return slackToken
    }

    override fun getSlackBot(): Bot {
        return this
    }

    /**
     * Invoked when the bot receives a direct mention (@botname: message)
     * or a direct message. NOTE: These two event types are added by jbot
     * to make your task easier, Slack doesn't have any direct way to
     * determine these type of events.

     * @param session
     * *
     * @param event
     */
    @Controller(events = arrayOf(EventType.DIRECT_MENTION, EventType.DIRECT_MESSAGE))
    fun onReceiveDM(session: WebSocketSession, event: Event) {
        reply(session, event, Message("Hi, I am " + slackService.currentUser.name))
    }

    /**
     * Invoked when bot receives an event of type message with text satisfying
     * the pattern `([a-z ]{2})(\d+)([a-z ]{2})`. For example,
     * messages like "ab12xy" or "ab2bc" etc will invoke this method.

     * @param session
     * *
     * @param event
     */
    @Controller(events = arrayOf(EventType.MESSAGE), pattern = "^([a-z ]{2})(\\d+)([a-z ]{2})$")
    fun onReceiveMessage(session: WebSocketSession, event: Event, matcher: Matcher) {
        reply(session, event, Message("First group: " + matcher.group(0) + "\n" +
                "Second group: " + matcher.group(1) + "\n" +
                "Third group: " + matcher.group(2) + "\n" +
                "Fourth group: " + matcher.group(3)))
    }

    /**
     * Invoked when an item is pinned in the channel.

     * @param session
     * *
     * @param event
     */
    @Controller(events = arrayOf(EventType.PIN_ADDED))
    fun onPinAdded(session: WebSocketSession, event: Event) {
        reply(session, event, Message("Thanks for the pin! You can find all pinned items under channel details."))
    }

    /**
     * Invoked when bot receives an event of type file shared.
     * NOTE: You can't reply to this event as slack doesn't send
     * a channel id for this event type. You can learn more about
     * [file_shared](https://api.slack.com/events/file_shared)
     * event from Slack's Api documentation.

     * @param session
     * *
     * @param event
     */
    @Controller(events = arrayOf(EventType.FILE_SHARED))
    fun onFileShared(session: WebSocketSession, event: Event) {
        LOG.info("File shared: {}", event)
    }


    /**
     * Conversation feature of JBot. This method is the starting point of the conversation (as it
     * calls [Bot.startConversation] within it. You can chain methods which will be invoked
     * one after the other leading to a conversation. You can chain methods with [Controller.next] by
     * specifying the method name to chain with.

     * @param session
     * *
     * @param event
     */
    @Controller(pattern = "(setup meeting)", next = "confirmTiming")
    fun setupMeeting(session: WebSocketSession, event: Event) {
        startConversation(event, "confirmTiming")   // start conversation
        reply(session, event, Message("Cool! At what time (ex. 15:30) do you want me to set up the meeting?"))
    }

    /**
     * This method is chained with [LunchBot.setupMeeting].

     * @param session
     * *
     * @param event
     */
    @Controller(next = "askTimeForMeeting")
    fun confirmTiming(session: WebSocketSession, event: Event) {
        reply(session, event, Message("Your meeting is set at " + event.text +
                ". Would you like to repeat it tomorrow?"))
        nextConversation(event)    // jump to next question in conversation
    }

    /**
     * This method is chained with [LunchBot.confirmTiming].

     * @param session
     * *
     * @param event
     */
    @Controller(next = "askWhetherToRepeat")
    fun askTimeForMeeting(session: WebSocketSession, event: Event) {
        if (event.text.contains("yes")) {
            reply(session, event, Message("Okay. Would you like me to set a reminder for you?"))
            nextConversation(event)    // jump to next question in conversation
        } else {
            reply(session, event, Message("No problem. You can always schedule one with 'setup meeting' command."))
            stopConversation(event)    // stop conversation only if user says no
        }
    }

    /**
     * This method is chained with [LunchBot.askTimeForMeeting].

     * @param session
     * *
     * @param event
     */
    @Controller
    fun askWhetherToRepeat(session: WebSocketSession, event: Event) {
        if (event.text.contains("yes")) {
            reply(session, event, Message("Great! I will remind you tomorrow before the meeting."))
        } else {
            reply(session, event, Message("Oh! my boss is smart enough to remind himself :)"))
        }
        stopConversation(event)    // stop conversation
    }
}
