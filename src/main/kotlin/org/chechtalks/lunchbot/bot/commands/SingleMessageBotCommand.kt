package org.chechtalks.lunchbot.bot.commands

import me.ramswaroop.jbot.core.slack.models.Message
import org.chechtalks.lunchbot.bot.messaging.BotResponse

interface SingleMessageBotCommand : BotCommand {

    override fun execute(response: BotResponse) = response.send(execute())

    fun execute(): Message

}