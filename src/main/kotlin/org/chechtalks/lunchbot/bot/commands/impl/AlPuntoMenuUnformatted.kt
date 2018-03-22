package org.chechtalks.lunchbot.bot.commands.impl

import me.ramswaroop.jbot.core.slack.models.Event
import me.ramswaroop.jbot.core.slack.models.Message
import org.chechtalks.lunchbot.bot.commands.AL_PUNTO_JUSTO
import org.chechtalks.lunchbot.bot.commands.MENU
import org.chechtalks.lunchbot.bot.commands.SIN_FORMATEAR
import org.chechtalks.lunchbot.bot.commands.SingleMessageBotCommand
import org.chechtalks.lunchbot.bot.model.DailyMenus
import org.chechtalks.lunchbot.bot.model.DailyMenus.Companion.isValidAlPuntoMenu
import org.chechtalks.lunchbot.config.MessageResolver
import org.chechtalks.lunchbot.extensions.contains
import org.chechtalks.lunchbot.social.FacebookHelper
import org.springframework.stereotype.Component

@Component
class AlPuntoMenuUnformatted(private val facebook: FacebookHelper, private val messages: MessageResolver) : SingleMessageBotCommand {

    override fun invoked(event: Event) = event.text.contains(MENU, AL_PUNTO_JUSTO, SIN_FORMATEAR)

    override fun execute(): Message {
        return Message((successResponse() ?: defaultResponse()))
    }

    override fun help() = messages.get("lunchbot.response.help.menu")

    internal fun successResponse() = facebook.getFirstPost("alpuntojusto", ::isValidAlPuntoMenu)

    internal fun defaultResponse() = messages.get("lunchbot.response.menu.notfound")
}
