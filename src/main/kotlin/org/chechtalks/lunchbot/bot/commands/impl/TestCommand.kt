package org.chechtalks.lunchbot.bot.commands.impl

import me.ramswaroop.jbot.core.slack.models.Event
import org.chechtalks.lunchbot.bot.commands.BotCommand
import org.chechtalks.lunchbot.bot.messaging.BotResponse
import org.chechtalks.lunchbot.config.MessageResolver
import org.chechtalks.lunchbot.extensions.contains
import org.chechtalks.lunchbot.slack.methods.ChatOperations
import org.springframework.stereotype.Component

@Component
class TestCommand(
        messages: MessageResolver,
        private val chatOperations: ChatOperations)
    : BotCommand {

    private lateinit var channel: String

    override fun invoked(event: Event): Boolean {
        if (event.text.contains("test command")) {
            channel = event.channelId
        }
        return event.text.contains("test command")
    }

    override fun execute(response: BotResponse) {
        chatOperations.postThreadedMessages(channel, "cocina soho", "> menu 1", "> menu 2")
    }
}
