package org.chechtalks.lunchbot.bot.commands.impl

import me.ramswaroop.jbot.core.slack.models.Event
import me.ramswaroop.jbot.core.slack.models.Message
import org.chechtalks.lunchbot.bot.commands.COCINA_SOHO
import org.chechtalks.lunchbot.bot.commands.MENU
import org.chechtalks.lunchbot.bot.commands.MultiMessageBotCommand
import org.chechtalks.lunchbot.bot.model.DailyMenus
import org.chechtalks.lunchbot.extensions.contains
import org.chechtalks.lunchbot.extensions.quoted
import org.springframework.stereotype.Component

@Component
class GetMenuFormatted(private val getMenu: GetMenu) : MultiMessageBotCommand {

    override fun invoked(event: Event) = event.text.contains(MENU, COCINA_SOHO) && !getMenu.invoked(event)

    override fun execute(): List<Message> {
        val dailyMenus = getMenu.successResponse() ?: return defaultResponse()

        return parse(dailyMenus)
                .map(::Message)
    }

    private fun parse(dailyMenus: String): List<String> {
        with(DailyMenus.from(dailyMenus)) {
            val messages = mutableListOf<String>().apply {
                add(beginning)
                addAll(menus.quoted())
                add(ending)
            }

            return messages.toList()
        }
    }

    private fun defaultResponse() = listOf(Message(getMenu.defaultResponse()))
}
