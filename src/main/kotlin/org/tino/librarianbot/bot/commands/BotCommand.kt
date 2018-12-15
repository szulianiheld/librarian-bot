package org.tino.librarianbot.bot.commands

import me.ramswaroop.jbot.core.slack.models.Event
import org.tino.librarianbot.bot.messaging.BotResponse

interface BotCommand {

    fun invoked(event: Event): Boolean

    fun execute(response: BotResponse)

    fun help(): String? = null
}