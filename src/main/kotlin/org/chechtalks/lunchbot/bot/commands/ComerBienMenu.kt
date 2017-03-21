package org.chechtalks.lunchbot.bot.commands

import me.ramswaroop.jbot.core.slack.models.Event
import org.chechtalks.lunchbot.bot.messaging.BotResponse
import org.chechtalks.lunchbot.config.MessageResolver
import org.chechtalks.lunchbot.extensions.contains
import org.springframework.stereotype.Component

@Component
class ComerBienMenu(private val messages: MessageResolver) : BotCommand {

    override fun invoked(event: Event) = event.text.contains("menu", "comer", "bien")

    override fun execute(response: BotResponse) {
        response.send(messages.get("lunchbot.response.menu.notfound.comerbien"))
    }
}
