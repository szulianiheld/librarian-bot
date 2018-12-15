package org.tino.librarianbot.bot.commands

import me.ramswaroop.jbot.core.slack.models.Message
import org.tino.librarianbot.bot.messaging.BotResponse

interface SingleMessageBotCommand : BotCommand {

    override fun execute(response: BotResponse) = response.send(execute())

    fun execute(): Message

}