package org.chechtalks.lunchbot.bot.commands.impl

import me.ramswaroop.jbot.core.slack.models.Event
import me.ramswaroop.jbot.core.slack.models.Message
import org.chechtalks.lunchbot.bot.commands.MultiMessageBotCommand
import org.chechtalks.lunchbot.config.MessageResolver
import org.chechtalks.lunchbot.extensions.contains
import org.chechtalks.lunchbot.extensions.preformatted
import org.chechtalks.lunchbot.social.FacebookHelper
import org.springframework.stereotype.Component

@Component
class SohoPosts(private val facebook: FacebookHelper, private val messages: MessageResolver) : MultiMessageBotCommand {

    override fun invoked(event: Event) = event.text.contains("posts", "soho")

    override fun execute(): List<Message> {
        val posts = facebook.getPosts("CocinaSoho")

        if (!posts.isEmpty()) {
            return posts.preformatted()
                    .map(::Message)
        } else {
            return defaultResponse()
        }
    }

    override fun help() = messages.get("lunchbot.response.help.posts")

    private fun defaultResponse() = listOf(Message(messages.get("lunchbot.response.posts.notfound")))
}
