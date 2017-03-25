package org.chechtalks.lunchbot.bot.commands

import me.ramswaroop.jbot.core.slack.models.Event
import me.ramswaroop.jbot.core.slack.models.Message
import org.chechtalks.lunchbot.config.MessageResolver
import org.chechtalks.lunchbot.extensions.contains
import org.springframework.stereotype.Component

@Component
class ComerBienMenu(private val messages: MessageResolver) : SingleMessageBotCommand {

    override fun invoked(event: Event) = event.text.contains("menu", "comer", "bien")

    override fun execute(): Message {
        return Message(messages.get("lunchbot.response.menu.notfound.comerbien"))
    }
}
