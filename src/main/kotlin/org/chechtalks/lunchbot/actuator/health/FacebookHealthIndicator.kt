package org.chechtalks.lunchbot.actuator.health

import org.chechtalks.lunchbot.social.FacebookHelper
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.stereotype.Component

@Component
class FacebookHealthIndicator(private val facebookHelper: FacebookHelper) : HealthIndicator {

    override fun health(): Health {
        return when {
            facebookHelper.facebook.isAuthorized -> Health.up().build()
            else -> Health.outOfService().build()
        }
    }

}