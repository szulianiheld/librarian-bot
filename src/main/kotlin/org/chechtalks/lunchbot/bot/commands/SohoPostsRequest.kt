package org.chechtalks.lunchbot.bot.commands

import me.ramswaroop.jbot.core.slack.models.Event
import org.chechtalks.lunchbot.bot.messaging.BotResponse
import org.chechtalks.lunchbot.config.MessageResolver
import org.chechtalks.lunchbot.extensions.contains
import org.chechtalks.lunchbot.extensions.preformatted
import org.chechtalks.lunchbot.social.FacebookHelper
import org.springframework.stereotype.Component

@Component
class SohoPostsRequest(private val facebook: FacebookHelper, private val messages: MessageResolver) : BotCommand {

    override fun invoked(event: Event) = event.text.contains("posts", "soho")

    override fun execute(response: BotResponse) {
        response.send(successResponse() ?: defaultResponse())
    }

    private fun successResponse(): String? {
        val posts = facebook.getPosts("CocinaSoho")

        return when {
            !posts.isEmpty() -> posts.preformatted()
            else -> null
        }
    }

    private fun defaultResponse() = messages.get("lunchbot.response.posts.notfound")
}
