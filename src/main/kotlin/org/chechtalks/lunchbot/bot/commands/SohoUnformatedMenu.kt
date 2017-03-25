package org.chechtalks.lunchbot.bot.commands

import me.ramswaroop.jbot.core.slack.models.Event
import me.ramswaroop.jbot.core.slack.models.Message
import org.chechtalks.lunchbot.bot.model.DailyMenus.Companion.isMenu
import org.chechtalks.lunchbot.config.MessageResolver
import org.chechtalks.lunchbot.extensions.contains
import org.chechtalks.lunchbot.social.FacebookHelper
import org.springframework.stereotype.Component

@Component
class SohoUnformatedMenu(private val facebook: FacebookHelper, private val messages: MessageResolver) : SingleMessageBotCommand {

    override fun invoked(event: Event) = event.text.contains("menu", "soho", "sin formatear")

    override fun execute(): Message {
        return Message((successResponse() ?: defaultResponse()))
    }

    private fun successResponse() = facebook.getFirstPost("CocinaSoho", ::isMenu)

    private fun defaultResponse() = messages.get("lunchbot.response.menu.notfound")
}
