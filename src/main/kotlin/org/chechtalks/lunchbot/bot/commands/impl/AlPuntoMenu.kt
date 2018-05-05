package org.chechtalks.lunchbot.bot.commands.impl

import me.ramswaroop.jbot.core.slack.models.Event
import me.ramswaroop.jbot.core.slack.models.Message
import org.chechtalks.lunchbot.bot.commands.AL_PUNTO_JUSTO
import org.chechtalks.lunchbot.bot.commands.MENU
import org.chechtalks.lunchbot.bot.commands.MultiMessageBotCommand
import org.chechtalks.lunchbot.bot.utils.MenuParser
import org.chechtalks.lunchbot.config.MessageResolver
import org.chechtalks.lunchbot.extensions.contains
import org.chechtalks.lunchbot.slack.methods.ChatOperations
import org.chechtalks.lunchbot.social.FacebookHelper
import org.springframework.stereotype.Component

@Component
class AlPuntoMenu(private val facebook: FacebookHelper,
                  private val messages: MessageResolver,
                  private val chatOperations: ChatOperations)
    : MultiMessageBotCommand {

    private lateinit var channel: String

    override fun invoked(event: Event): Boolean {
        val invoked = event.text.contains(MENU, AL_PUNTO_JUSTO)
        if (invoked) {
            channel = event.channelId
        }
        return invoked
    }

    override fun execute(): List<Message> {
        val menuPost = successResponse() ?: return defaultResponse()
        val parsedLines = MenuParser.parseAlPunto(menuPost)
        chatOperations.postThreadedMessages(channel, "Al punto justo :point_down:", parsedLines)

        return emptyList()
    }

    override fun help() = messages.get("lunchbot.response.help.menu")

    internal fun successResponse() = facebook.getFirstPost("alpuntojusto", MenuParser.Companion::isValidAlPuntoMenu)

    internal fun defaultResponse() = listOf(Message(messages.get("lunchbot.response.menu.notfound")))
}
