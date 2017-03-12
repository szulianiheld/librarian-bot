package org.chechtalks.lunchbot

import me.ramswaroop.jbot.core.slack.Bot
import me.ramswaroop.jbot.core.slack.Controller
import me.ramswaroop.jbot.core.slack.EventType
import me.ramswaroop.jbot.core.slack.models.Event
import org.chechtalks.lunchbot.messaging.Message
import org.chechtalks.lunchbot.messaging.Response
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.socket.WebSocketSession

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

    @Controller(events = arrayOf(EventType.DIRECT_MENTION, EventType.DIRECT_MESSAGE))
    fun onMessage(session: WebSocketSession, event: Event) {

        val message = Message(event.text)
        val response = Response(this, session, event)

        when {
            message.contains("menu", "soho") -> response.send("me preguntó el menu en Soho")
            message.contains("menu", "comer", "bien") -> response.send("me preguntó el menu en Comer Bien")
            else -> response.send("No entendí")
        }
    }
}
