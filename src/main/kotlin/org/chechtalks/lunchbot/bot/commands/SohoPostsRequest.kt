package org.chechtalks.lunchbot.bot.commands

import me.ramswaroop.jbot.core.slack.models.Event
import org.chechtalks.lunchbot.bot.messaging.BotResponse
import org.chechtalks.lunchbot.extensions.contains
import org.chechtalks.lunchbot.extensions.prettyToString
import org.chechtalks.lunchbot.social.FacebookHelper
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class SohoPostsRequest(private val facebook: FacebookHelper) : BotCommand {

    val NOT_FOUND = "No encontré ningún post de hoy. Tal vez todavía no escribieron nada :thinking_face:"

    override fun invoked(event: Event) = event.text.contains("posts", "soho")

    override fun execute(response: BotResponse) {
        val posts = facebook.getPosts("CocinaSoho")
        response.send(if (!posts.isEmpty()) posts.prettyToString() else NOT_FOUND)
    }
}
