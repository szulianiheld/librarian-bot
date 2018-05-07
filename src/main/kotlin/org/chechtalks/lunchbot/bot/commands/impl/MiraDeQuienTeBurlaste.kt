package org.chechtalks.lunchbot.bot.commands.impl

import me.ramswaroop.jbot.core.slack.models.Event
import me.ramswaroop.jbot.core.slack.models.Message
import org.chechtalks.lunchbot.bot.commands.SE_BURLARON
import org.chechtalks.lunchbot.bot.commands.SingleMessageBotCommand
import org.chechtalks.lunchbot.config.MessageResolver
import org.chechtalks.lunchbot.extensions.contains
import org.springframework.stereotype.Component

@Component
class MiraDeQuienTeBurlaste(
        private val messages: MessageResolver)
    : SingleMessageBotCommand {

    override fun invoked(event: Event) = event.text.contains(SE_BURLARON)

    override fun execute(): Message {
        return Message(messages.get("lunchbot.response.teburlaste"))
    }
}
