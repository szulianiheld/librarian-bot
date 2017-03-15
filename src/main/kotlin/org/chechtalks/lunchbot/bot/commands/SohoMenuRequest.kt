package org.chechtalks.lunchbot.bot.commands

import me.ramswaroop.jbot.core.slack.models.Event
import org.chechtalks.lunchbot.bot.messaging.BotResponse
import org.chechtalks.lunchbot.extensions.contains
import org.chechtalks.lunchbot.social.FacebookHelper
import org.springframework.stereotype.Component

@Component
class SohoMenuRequest(private val facebook: FacebookHelper) : BotCommand {

    val SOHO_MENU_NOT_FOUND = "No encontré el menú de hoy. Tal vez todavía no lo subieron :thinking_face:"

    override fun invoked(event: Event) = event.text.contains("menu", "soho")

    override fun execute(response: BotResponse) {
        response.send(facebook.getFirstPost("CocinaSoho", "Hoy") ?: SOHO_MENU_NOT_FOUND)
    }
}
