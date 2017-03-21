package org.chechtalks.lunchbot.bot.commands

import me.ramswaroop.jbot.core.slack.models.Event
import org.chechtalks.lunchbot.bot.messaging.BotResponse

interface BotCommand {

    fun invoked(event: Event): Boolean

    fun execute(response: BotResponse)

    fun help(): String? = null
}