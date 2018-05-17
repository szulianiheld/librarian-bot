package org.chechtalks.lunchbot.social

import org.chechtalks.lunchbot.extensions.getPosts
import org.springframework.core.env.Environment
import org.springframework.social.facebook.api.impl.FacebookTemplate
import org.springframework.social.facebook.connect.FacebookConnectionFactory
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class FacebookHelper(env: Environment) {

    lateinit var facebook: FacebookTemplate

    init {
        val appId = env.getProperty("spring.social.facebook.appId")
        val appSecret = env.getProperty("spring.social.facebook.appSecret")
        val token = getFacebookAccessToken(appId, appSecret)

        facebook = FacebookTemplate(token)
        facebook.setApiVersion("2.9")
    }

    fun getFirstPost(user: String, predicate: (String) -> Boolean, date: LocalDate = LocalDate.now()): String? {
        return facebook.getPosts(user, date)
                .map { it.message }
                .find { predicate(it) }
    }

    fun getPosts(user: String, date: LocalDate = LocalDate.now()): List<String> {
        return facebook.getPosts(user, date)
                .map { it.message }
    }

    private fun getFacebookAccessToken(appId: String, appSecret: String): String {
        val oauth = FacebookConnectionFactory(appId, appSecret).oAuthOperations
        return oauth.authenticateClient().accessToken
    }

}
