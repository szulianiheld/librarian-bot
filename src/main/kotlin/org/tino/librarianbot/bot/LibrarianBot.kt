package org.tino.librarianbot.bot

import me.ramswaroop.jbot.core.slack.Bot
import me.ramswaroop.jbot.core.slack.Controller
import me.ramswaroop.jbot.core.slack.EventType
import me.ramswaroop.jbot.core.slack.models.Event
import org.tino.librarianbot.bot.commands.BotCommand
import org.tino.librarianbot.bot.messaging.BotResponse
import org.tino.librarianbot.config.MessageResolver
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import org.springframework.web.socket.WebSocketSession
import javax.annotation.PostConstruct

@Component
class LibrarianBot(private val env: Environment, private val commands: List<BotCommand>, private val messages: MessageResolver) : Bot() {

    @PostConstruct
    fun setup() {
        BotUser.id = slackService.currentUser?.id ?: "mock-user"
    }

    override fun getSlackToken() = env.getProperty("slackBotToken")!!

    override fun getSlackBot() = this

    @Controller(events = arrayOf(EventType.DIRECT_MENTION, EventType.DIRECT_MESSAGE))
    fun onMessage(session: WebSocketSession, event: Event) {

        val response = BotResponse(this, session, event)

        commands.filter { it.invoked(event) }
                .also { handleUnrecognizedCommand(it, response) }
                .forEach { it.execute(response) }
    }

    private fun handleUnrecognizedCommand(it: List<BotCommand>, response: BotResponse) {
        if (it.isEmpty())
            response.send(messages.get("librarianbot.response.default"))
    }
}