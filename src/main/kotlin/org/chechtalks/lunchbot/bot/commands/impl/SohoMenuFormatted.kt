package org.chechtalks.lunchbot.bot.commands.impl

import me.ramswaroop.jbot.core.slack.models.Event
import me.ramswaroop.jbot.core.slack.models.Message
import org.chechtalks.lunchbot.bot.commands.COCINA_SOHO
import org.chechtalks.lunchbot.bot.commands.MENU
import org.chechtalks.lunchbot.bot.commands.MultiMessageBotCommand
import org.chechtalks.lunchbot.bot.utils.MenuParser
import org.chechtalks.lunchbot.extensions.contains
import org.chechtalks.lunchbot.slack.methods.ChatOperations
import org.springframework.stereotype.Component

@Component
class SohoMenuFormatted(
        private val sohoMenuUnformatted: SohoMenuUnformatted,
        private val chatOperations: ChatOperations)
    : MultiMessageBotCommand {

    private lateinit var channel: String

    override fun invoked(event: Event): Boolean {
        val invoked = event.text.contains(MENU, COCINA_SOHO) && !sohoMenuUnformatted.invoked(event)
        if (invoked) {
            channel = event.channelId
        }
        return invoked
    }

    override fun execute(): List<Message> {
        val menuPost = sohoMenuUnformatted.successResponse() ?: return defaultResponse()
        val parsedLines = MenuParser.parseSoho(menuPost)
        chatOperations.postThreadedMessages(channel, "Cocina soho :point_down:", parsedLines)

        return emptyList()
    }

    private fun defaultResponse() = listOf(Message(sohoMenuUnformatted.defaultResponse()))
}
