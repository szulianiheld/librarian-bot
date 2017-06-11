package org.chechtalks.lunchbot.bot.model

import org.chechtalks.lunchbot.extensions.contains
import org.chechtalks.lunchbot.extensions.dropFirstNonLetters
import org.chechtalks.lunchbot.extensions.humanToString

data class DailyMenus(val beginning: String, val menus: List<String>, val ending: String) {

    companion object {

        fun from(rawMenu: String): DailyMenus {

            if (!isMenu(rawMenu)) throw IllegalArgumentException("\"$rawMenu\" is not a valid menu")

            val relevantLines = rawMenu
                    .split("\n")
                    .filter { it.length > 5 }
                    .map { it.dropFirstNonLetters() }

            val options = relevantLines
                    .filter { it.contains("$") }

            val beginning = relevantLines
                    .takeWhile { it != options.first() }
                    .humanToString()

            val ending = relevantLines
                    .takeLastWhile { it != options.last() }
                    .humanToString()

            return DailyMenus(beginning, options, ending)
        }

        fun isMenu(post: String): Boolean {
            val atLeast3Menus = Regex(""".+\$.+\$.+\$.+""", RegexOption.DOT_MATCHES_ALL)
            return post.matches(atLeast3Menus) && post.length > 20
        }

    }

}
