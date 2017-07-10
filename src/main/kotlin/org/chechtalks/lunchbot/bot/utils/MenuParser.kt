package org.chechtalks.lunchbot.bot.utils

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.chechtalks.lunchbot.bot.model.DailyMenus
import org.chechtalks.lunchbot.extensions.quoted
import org.springframework.stereotype.Component
import java.net.URL

@Component
class MenuParser(val menuBeautifier: (String) -> String = String::quoted) {

    fun parse(rawTextMenu: String): List<String> {
        val dailyMenus = DailyMenus.from(rawTextMenu)
        return parse(dailyMenus)
    }

    fun parse(jsonMenu: URL): List<String> {
        val dailyMenusList = jacksonObjectMapper().readValue<List<DailyMenus>>(jsonMenu)
        return dailyMenusList
                .map { parse(it) }
                .flatten()
    }

    fun parse(dailyMenus: DailyMenus): List<String> {
        with(dailyMenus) {
            val prettyMenus = menus.map(menuBeautifier)

            val messages = mutableListOf<String>().apply {
                add(beginning)
                addAll(prettyMenus)
                add(ending)
            }

            return messages.toList()
        }
    }
}