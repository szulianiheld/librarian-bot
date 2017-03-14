package org.chechtalks.lunchbot.bot

import me.ramswaroop.jbot.core.slack.Bot
import me.ramswaroop.jbot.core.slack.Controller
import me.ramswaroop.jbot.core.slack.EventType
import me.ramswaroop.jbot.core.slack.models.Event
import org.chechtalks.lunchbot.extensions.contains
import org.chechtalks.lunchbot.social.FacebookHelper
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import org.springframework.web.socket.WebSocketSession

@Component
class LunchBot(private val env: Environment, private val facebook: FacebookHelper) : Bot() {

    private val LOG = LoggerFactory.getLogger(LunchBot::class.java)

    val SOHO_MENU_NOT_FOUND = "No encontre el menu de hoy para Cocina Soho :thinking_face:"
    val COMER_BIEN_MENU_NOT_FOUND = "Todavia no se buscar el menu de Comer Bien :sweat_smile:"
    val DEFAULT_RESPONSE = "No entendí :neutral_face:"

    override fun getSlackToken(): String {
        val slackToken = env.getProperty("slackBotToken")
        LOG.trace("getting slack token : $slackToken")

        return slackToken
    }

    override fun getSlackBot(): Bot {
        return this
    }

    @Controller(events = arrayOf(EventType.DIRECT_MENTION, EventType.DIRECT_MESSAGE))
    fun onMessage(session: WebSocketSession, event: Event) {

        val response = BotResponse(this, session, event)

        when {
            cocinaSohoMenuCommandInvoked(event) -> cocinaSohoMenuCommand(response)
            commerBienMenuCommandInvoked(event) -> comerBienMenuCommand(response)
            else -> defaultResponseCommand(response)
        }
    }

    private fun commerBienMenuCommandInvoked(event: Event) = event.text.contains("menu", "comer", "bien")

    private fun cocinaSohoMenuCommandInvoked(event: Event) = event.text.contains("menu", "soho")

    private fun defaultResponseCommand(response: BotResponse) {
        response.send(DEFAULT_RESPONSE)
    }

    private fun comerBienMenuCommand(response: BotResponse) {
        response.send(COMER_BIEN_MENU_NOT_FOUND)
    }

    private fun cocinaSohoMenuCommand(response: BotResponse) {
        response.send(facebook.getFirstMessage("CocinaSoho", "Hoy") ?: SOHO_MENU_NOT_FOUND)
    }
}
