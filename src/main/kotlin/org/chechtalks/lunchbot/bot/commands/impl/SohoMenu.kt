package org.chechtalks.lunchbot.bot.commands.impl

import me.ramswaroop.jbot.core.slack.models.Event
import me.ramswaroop.jbot.core.slack.models.Message
import org.chechtalks.lunchbot.bot.commands.COCINA_SOHO
import org.chechtalks.lunchbot.bot.commands.MENU
import org.chechtalks.lunchbot.bot.commands.MultiMessageBotCommand
import org.chechtalks.lunchbot.bot.utils.MenuParser
import org.chechtalks.lunchbot.config.MessageResolver
import org.chechtalks.lunchbot.extensions.contains
import org.chechtalks.lunchbot.slack.methods.ChatOperations
import org.chechtalks.lunchbot.social.FacebookHelper
import org.springframework.stereotype.Component

@Component
class SohoMenu(
        private val facebook: FacebookHelper,
        private val messages: MessageResolver,
        private val chatOperations: ChatOperations)
    : MultiMessageBotCommand {

    private lateinit var channel: String

    override fun invoked(event: Event): Boolean {
        val invoked = event.text.contains(MENU, COCINA_SOHO)
        if (invoked) {
            channel = event.channelId
        }
        return invoked
    }

    override fun execute(): List<Message> {
        val menuPost = successResponse() ?: return defaultResponse()
        val parsedLines = MenuParser.parseSoho(menuPost)
        chatOperations.postThreadedMessages(channel, "Cocina soho :point_down:", parsedLines)

        return emptyList()
    }

    override fun help() = messages.get("lunchbot.response.help.menu")

    internal fun successResponse() = facebook.getFirstPost("CocinaSoho", MenuParser.Companion::isValidSohoMenu)

    internal fun defaultResponse() = listOf(Message(messages.get("lunchbot.response.menu.notfound")))
}