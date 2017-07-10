package org.chechtalks.lunchbot.bot.commands.impl

import me.ramswaroop.jbot.core.slack.models.Event
import me.ramswaroop.jbot.core.slack.models.Message
import org.chechtalks.lunchbot.bot.commands.LO_DE_ROSA
import org.chechtalks.lunchbot.bot.commands.MENU
import org.chechtalks.lunchbot.bot.commands.MultiMessageBotCommand
import org.chechtalks.lunchbot.bot.utils.MenuParser
import org.chechtalks.lunchbot.extensions.contains
import org.springframework.stereotype.Component

@Component
class RosaMenuFormatted(private val menuParser: MenuParser) : MultiMessageBotCommand {

    private val JSON_MENUS = "/menu/lo-de-rosa.json"

    override fun invoked(event: Event) = event.text.contains(MENU, LO_DE_ROSA)

    override fun execute(): List<Message> {
        val rosaMenus = this.javaClass.getResource(JSON_MENUS)

        return menuParser
                .parse(rosaMenus)
                .map(::Message)
    }
}