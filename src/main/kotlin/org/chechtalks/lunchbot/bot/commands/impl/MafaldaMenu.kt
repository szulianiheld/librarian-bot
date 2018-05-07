package org.chechtalks.lunchbot.bot.commands.impl

import me.ramswaroop.jbot.core.slack.models.Event
import me.ramswaroop.jbot.core.slack.models.Message
import org.chechtalks.lunchbot.bot.commands.MAFALDA
import org.chechtalks.lunchbot.bot.commands.MENU
import org.chechtalks.lunchbot.bot.commands.MultiMessageBotCommand
import org.chechtalks.lunchbot.bot.utils.MenuParser
import org.chechtalks.lunchbot.extensions.contains
import org.chechtalks.lunchbot.slack.methods.ChatOperations
import org.springframework.stereotype.Component

@Component
class MafaldaMenu(
        private val chatOperations: ChatOperations)
    : MultiMessageBotCommand {

    private val JSON_MENUS = "/menu/mafalda.json"

    private lateinit var channel: String

    override fun invoked(event: Event): Boolean {
        val invoked = event.text.contains(MENU, MAFALDA)
        if (invoked) {
            channel = event.channelId
        }
        return invoked
    }

    override fun execute(): List<Message> {
        val jsonMenus = this.javaClass.getResource(JSON_MENUS)
        val parsedMenus = MenuParser.parse(jsonMenus)
        chatOperations.postThreadedMessages(channel, "Mafalda :point_down:", parsedMenus)

        return emptyList()
    }
}
