package org.chechtalks.lunchbot.bot.model

import org.chechtalks.lunchbot.extensions.contains
import org.chechtalks.lunchbot.extensions.dropFirstNonLetters
import org.chechtalks.lunchbot.extensions.humanToString

data class DailyMenus(val beginning: String, val menus: List<String>, val ending: String) {

    companion object {

        fun from(rawMenu: String, isMenu: (String) -> Boolean): DailyMenus {
            val relevantLines = parseRelevantLines(rawMenu)
            val options = parseMenuOptions(relevantLines, isMenu)
            val beginning = parseBeginning(relevantLines, options)
            val ending = parseEnding(relevantLines, options)
            return DailyMenus(beginning, options, ending)
        }

        fun fromSoho(rawMenu: String) = from(rawMenu) { it.contains("$") }

        fun fromAlPunto(rawMenu: String) = from(rawMenu) { it.contains("- ") }

        fun isValidSohoMenu(rawMenu: String): Boolean {
            val atLeast3Menus = Regex(""".+\$.+\$.+\$.+""", RegexOption.DOT_MATCHES_ALL)
            return rawMenu.matches(atLeast3Menus) && rawMenu.length > 20
        }

        fun isValidAlPuntoMenu(rawMenu: String): Boolean {
            val atLeast3Menus = Regex(""".+\n-.+\n-.+\n-.+""", RegexOption.DOT_MATCHES_ALL)
            return rawMenu.matches(atLeast3Menus) && rawMenu.length > 20
        }

        private fun parseRelevantLines(rawMenu: String): List<String> {
            return rawMenu
                    .split("\n")
                    .filter { it.length > 5 }
                    .map { it.dropFirstNonLetters() }
        }

        private fun parseMenuOptions(relevantLines: List<String>, isMenu: (String) -> Boolean): List<String> {
            return relevantLines
                    .filter { isMenu(it) }
        }

        private fun parseBeginning(relevantLines: List<String>, options: List<String>): String {
            return relevantLines
                    .takeWhile { it != options.first() }
                    .humanToString()
        }

        private fun parseEnding(relevantLines: List<String>, options: List<String>): String {
            return relevantLines
                    .takeLastWhile { it != options.last() }
                    .humanToString()
        }
    }
}
