package org.tino.librarianbot.bot.commands.impl

import me.ramswaroop.jbot.core.slack.models.Event
import me.ramswaroop.jbot.core.slack.models.Message
import org.tino.librarianbot.bot.commands.HELP
import org.tino.librarianbot.bot.commands.BotCommand
import org.tino.librarianbot.bot.commands.SingleMessageBotCommand
import org.tino.librarianbot.config.MessageResolver
import org.tino.librarianbot.extensions.DOUBLE_JUMP
import org.tino.librarianbot.extensions.contains
import org.springframework.stereotype.Component

@Component
class Help(private val messages: MessageResolver, private val commands: List<BotCommand>) : SingleMessageBotCommand {

    override fun invoked(event: Event) = event.text.contains(HELP)

    override fun execute(): Message {
        var message = help() + DOUBLE_JUMP

        commands.map { it.help() }
                .filterNotNull()
                .forEach { message += "- $it$DOUBLE_JUMP" }

        return Message(message)
    }

    override fun help() = messages.get("lunchbot.response.help")
}
