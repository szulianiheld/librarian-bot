package org.chechtalks.lunchbot.bot.utils

import org.chechtalks.lunchbot.constants.MENU_POST_1
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.startsWith
import org.junit.Assert.assertThat
import org.junit.Test

class MenuParserTest {

    private val JSON_MENUS = "/menu/lo-de-rosa.json"

    private val menuParser = MenuParser()

    @Test
    fun it_parses_a_raw_text() {
        val result = menuParser.parse(MENU_POST_1)
        assertThat(result.size, `is`(9))
    }

    @Test
    fun it_parses_a_json_file() {
        val rosaMenus = this.javaClass.getResource(JSON_MENUS)
        val result = menuParser.parse(rosaMenus)

        assertThat(result.size, `is`(38))
        assertThat(result[1], `is`("> Carne"))
        assertThat(result[32], startsWith("> Calabresa"))
    }

}