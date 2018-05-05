package org.chechtalks.lunchbot.bot.utils

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.net.URL

class MenuParser {
    companion object {

        fun parseSoho(rawTextMenu: String): List<String> {
            return rawTextMenu
                    .split("\n\n")
                    .filter { it.isNotEmpty() }
        }

        fun parseAlPunto(rawTextMenu: String): List<String> {
            return rawTextMenu
                    .split("\n")
                    .filter { it.isNotEmpty() }
        }

        fun parse(jsonMenu: URL): List<String> {
            val dailyMenusList = jacksonObjectMapper().readValue<List<String>>(jsonMenu)
            return dailyMenusList
        }

        fun isValidSohoMenu(rawMenu: String): Boolean {
            val atLeast3Menus = Regex(""".+\$.+\$.+\$.+""", RegexOption.DOT_MATCHES_ALL)
            return rawMenu.matches(atLeast3Menus) && rawMenu.length > 20
        }

        fun isValidAlPuntoMenu(rawMenu: String): Boolean {
            val atLeast3Menus = Regex(""".+\n-.+\n-.+\n-.+""", RegexOption.DOT_MATCHES_ALL)
            return rawMenu.matches(atLeast3Menus) && rawMenu.length > 20
        }
    }
}