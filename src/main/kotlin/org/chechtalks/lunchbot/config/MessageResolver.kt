package org.chechtalks.lunchbot.config

import org.chechtalks.lunchbot.bot.LunchBot
import org.slf4j.LoggerFactory
import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import java.util.*

@Service
class MessageResolver(val messageSource: MessageSource) {

    private val LOG = LoggerFactory.getLogger(LunchBot::class.java)

    fun get(code: String): String {
        try {
            return messageSource.getMessage(code, null, Locale("es"))
        } catch (e: RuntimeException) {
            LOG.warn(e.localizedMessage)
            return code
        }
    }

}