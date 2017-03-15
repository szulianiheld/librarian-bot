package org.chechtalks.lunchbot.bot.commands

import me.ramswaroop.jbot.core.slack.models.Event
import org.chechtalks.lunchbot.bot.messaging.BotResponse
import org.chechtalks.lunchbot.extensions.contains
import org.chechtalks.lunchbot.social.FacebookHelper
import org.springframework.stereotype.Component

@Component
class ComerBienMenuRequest(private val facebook: FacebookHelper) : BotCommand {

    val COMER_BIEN_MENU_NOT_FOUND = "Todavía no sé buscar el menú de Comer Bien :sweat_smile:"

    override fun invoked(event: Event) = event.text.contains("menu", "comer", "bien")

    override fun execute(response: BotResponse) {
        response.send(COMER_BIEN_MENU_NOT_FOUND)
    }
}
