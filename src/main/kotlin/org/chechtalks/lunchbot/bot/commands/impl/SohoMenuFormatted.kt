package org.chechtalks.lunchbot.bot.commands.impl

import me.ramswaroop.jbot.core.slack.models.Event
import me.ramswaroop.jbot.core.slack.models.Message
import org.chechtalks.lunchbot.bot.commands.COCINA_SOHO
import org.chechtalks.lunchbot.bot.commands.MENU
import org.chechtalks.lunchbot.bot.commands.MultiMessageBotCommand
import org.chechtalks.lunchbot.bot.utils.MenuParser
import org.chechtalks.lunchbot.extensions.contains
import org.springframework.stereotype.Component

@Component
class SohoMenuFormatted(
        private val sohoMenuUnformatted: SohoMenuUnformatted,
        private val menuParser: MenuParser)
    : MultiMessageBotCommand {

    override fun invoked(event: Event) = event.text.contains(MENU, COCINA_SOHO) && !sohoMenuUnformatted.invoked(event)

    override fun execute(): List<Message> {
        val sohoMenus = sohoMenuUnformatted.successResponse() ?: return defaultResponse()

        return menuParser
                .parse(sohoMenus)
                .map(::Message)
    }

    private fun defaultResponse() = listOf(Message(sohoMenuUnformatted.defaultResponse()))
}
