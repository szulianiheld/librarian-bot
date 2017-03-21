package org.chechtalks.lunchbot.bot.messaging

import me.ramswaroop.jbot.core.slack.Bot
import me.ramswaroop.jbot.core.slack.models.Event
import me.ramswaroop.jbot.core.slack.models.Message
import org.springframework.web.socket.WebSocketSession

class BotResponse(val bot: Bot, val session: WebSocketSession, val event: Event) {

    fun send(message: String) = bot.reply(session, event, Message(message))

}