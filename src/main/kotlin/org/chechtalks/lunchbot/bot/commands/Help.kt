package org.chechtalks.lunchbot.bot.commands

import me.ramswaroop.jbot.core.slack.models.Event
import me.ramswaroop.jbot.core.slack.models.Message
import org.chechtalks.lunchbot.config.MessageResolver
import org.chechtalks.lunchbot.extensions.contains
import org.chechtalks.lunchbot.extensions.quoted
import org.springframework.stereotype.Component

@Component
class Help(private val messages: MessageResolver, private val commands: List<BotCommand>) : SingleMessageBotCommand {

    override fun invoked(event: Event) = event.text.contains("ayuda")

    override fun execute(): Message {
        var message = help()

        commands.also { message += "\n\n" }
                .filter { it.help() != null }
                .forEach { message += it.help()!!.quoted() + "\n\n" }

        return Message(message)
    }

    override fun help() = messages.get("lunchbot.response.help")
}
