package org.chechtalks.lunchbot.extensions

import org.springframework.social.facebook.api.Facebook
import org.springframework.social.facebook.api.Post
import java.time.LocalDate

fun Post.isFrom(date: LocalDate): Boolean {
    val postDate = this.createdTime.toLocalDate()
    return postDate == date
}

fun Facebook.getPosts(user: String, date: LocalDate = LocalDate.now()): List<Post> {
    return this.feedOperations()
            .getPosts(user)
            .filter { it.isFrom(date) }
}
