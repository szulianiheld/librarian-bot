package org.chechtalks.lunchbot.bot.commands

import me.ramswaroop.jbot.core.slack.models.Event
import org.chechtalks.lunchbot.bot.messaging.BotResponse
import org.chechtalks.lunchbot.bot.model.DailyMenus
import org.chechtalks.lunchbot.bot.model.DailyMenus.Companion.isMenu
import org.chechtalks.lunchbot.config.MessageResolver
import org.chechtalks.lunchbot.extensions.contains
import org.chechtalks.lunchbot.extensions.quoted
import org.chechtalks.lunchbot.social.FacebookHelper
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class SohoFormatedMenu(private val facebook: FacebookHelper, private val messages: MessageResolver) : BotCommand {

    override fun invoked(event: Event): Boolean {
        return with(event.text) {
            contains("menu", "soho") && !contains("sin formatear")
        }
    }

    override fun execute(response: BotResponse) {
        val menu = facebook.getFirstPost("CocinaSoho", ::isMenu)

        if (menu == null) {
            defaultResponse(response)
            return
        }

        with(DailyMenus.from(menu)) {
            response.send(beginning)
            menus.forEach { response.send(it.quoted()) }
            response.send(ending)
        }
    }

    override fun help() = messages.get("lunchbot.response.help.menu")

    private fun defaultResponse(response: BotResponse) = response.send(messages.get("lunchbot.response.menu.notfound"))
}
