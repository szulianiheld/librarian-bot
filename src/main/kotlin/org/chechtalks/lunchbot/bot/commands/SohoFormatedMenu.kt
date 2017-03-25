package org.chechtalks.lunchbot.bot.commands

import me.ramswaroop.jbot.core.slack.models.Event
import me.ramswaroop.jbot.core.slack.models.Message
import org.chechtalks.lunchbot.bot.model.DailyMenus
import org.chechtalks.lunchbot.bot.model.DailyMenus.Companion.isMenu
import org.chechtalks.lunchbot.config.MessageResolver
import org.chechtalks.lunchbot.extensions.contains
import org.chechtalks.lunchbot.extensions.quoted
import org.chechtalks.lunchbot.social.FacebookHelper
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class SohoFormatedMenu(private val facebook: FacebookHelper, private val messages: MessageResolver) : MultiMessageBotCommand {

    override fun invoked(event: Event): Boolean {
        return with(event.text) {
            contains("menu", "soho") && !contains("sin formatear")
        }
    }

    override fun execute(): List<Message> {
        val menu = facebook.getFirstPost("CocinaSoho", ::isMenu, LocalDate.of(2017, 3, 22))
                ?: return defaultResponse()

        return parseDailyMenus(menu)
                .map(::Message)
    }

    override fun help() = messages.get("lunchbot.response.help.menu")

    private fun parseDailyMenus(menu: String): MutableList<String> {
        with(DailyMenus.from(menu)) {
            val list = mutableListOf(beginning)
            list.addAll(menus.quoted())
            list.add(ending)

            return list
        }
    }

    private fun defaultResponse() = listOf(Message(messages.get("lunchbot.response.menu.notfound")))
}
