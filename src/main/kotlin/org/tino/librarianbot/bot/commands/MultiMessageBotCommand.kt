package org.tino.librarianbot.bot.commands

import me.ramswaroop.jbot.core.slack.models.Message
import org.tino.librarianbot.bot.messaging.BotResponse

interface MultiMessageBotCommand : BotCommand {

    override fun execute(response: BotResponse) = execute().forEach { response.send(it) }

    fun execute(): List<Message>

}