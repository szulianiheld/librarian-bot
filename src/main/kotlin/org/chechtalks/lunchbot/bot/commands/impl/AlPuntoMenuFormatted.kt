package org.chechtalks.lunchbot.bot.commands.impl

import me.ramswaroop.jbot.core.slack.models.Event
import me.ramswaroop.jbot.core.slack.models.Message
import org.chechtalks.lunchbot.bot.commands.AL_PUNTO_JUSTO
import org.chechtalks.lunchbot.bot.commands.MENU
import org.chechtalks.lunchbot.bot.commands.MultiMessageBotCommand
import org.chechtalks.lunchbot.bot.utils.MenuParser
import org.chechtalks.lunchbot.extensions.contains
import org.springframework.stereotype.Component

@Component
class AlPuntoMenuFormatted(
        private val alPuntoMenuUnformatted: AlPuntoMenuUnformatted)
    : MultiMessageBotCommand {

    override fun invoked(event: Event) = event.text.contains(MENU, AL_PUNTO_JUSTO) && !alPuntoMenuUnformatted.invoked(event)

    override fun execute(): List<Message> {
        val sohoMenus = alPuntoMenuUnformatted.successResponse() ?: return defaultResponse()

        return MenuParser
                .parseAlPunto(sohoMenus)
                .map(::Message)
    }

    private fun defaultResponse() = listOf(Message(alPuntoMenuUnformatted.defaultResponse()))
}
