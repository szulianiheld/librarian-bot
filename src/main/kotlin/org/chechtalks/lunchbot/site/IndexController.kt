package org.chechtalks.lunchbot.site

import org.springframework.social.connect.ConnectionRepository
import org.springframework.social.facebook.api.Facebook
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/")
class IndexController(private val connectionRepository: ConnectionRepository) {

    @GetMapping
    fun index(model: Model): String {
        if (connectionRepository.findPrimaryConnection(Facebook::class.java) == null) {
            return "redirect:/connect/facebook"
        }

        return "index"
    }

}