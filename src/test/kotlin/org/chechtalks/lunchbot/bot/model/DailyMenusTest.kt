package org.chechtalks.lunchbot.bot.model

import org.chechtalks.lunchbot.constants.*
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.assertThat
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DailyMenusTest {

    @Test
    fun it_validates_posts() {
        assertTrue(DailyMenus.isMenu(MENU_POST_1))
        assertTrue(DailyMenus.isMenu(MENU_POST_2))
        assertTrue(DailyMenus.isMenu(MENU_POST_3))
        assertTrue(DailyMenus.isMenu(MENU_POST_4))
        assertFalse(DailyMenus.isMenu(NOT_MENU_POST_1))
        assertFalse(DailyMenus.isMenu(NOT_MENU_POST_2))
    }

    @Test
    fun it_removes_leading_crap_in_menues() {
        val parsedOptions = DailyMenus.from(MENU_POST_1).menus

        assertThat(parsedOptions[0], startsWith("BIFE DE CHORIZO"))
        assertThat(parsedOptions[1], startsWith("QUESADILLA de VEGETALES"))
        assertThat(parsedOptions[2], startsWith("TARTA del DIA"))
    }

    @Test
    fun it_parses_case1() {
        val parsedMenu = DailyMenus.from(MENU_POST_1)

        assertThat(parsedMenu.menus.size, `is`(7))
        parsedMenu.menus.forEach { assertThat(it, containsString("$")) }

        assertThat(parsedMenu.beginning, containsString("HOY - Viernes - Mediodía en Cocina Soho!!"))
        assertThat(parsedMenu.beginning, containsString("Para este mediodia te propongo:"))
        assertThat(parsedMenu.beginning, not(containsString(parsedMenu.menus.first())))

        assertThat(parsedMenu.ending, not(containsString(parsedMenu.menus.last())))
        assertThat(parsedMenu.ending, containsString("PARA PEDIDOS y CONSULTAS"))
    }

    @Test
    fun it_parses_case2() {
        val parsedMenu = DailyMenus.from(MENU_POST_2)

        assertThat(parsedMenu.menus.size, `is`(7))
        parsedMenu.menus.forEach { assertThat(it, containsString("$")) }

        assertThat(parsedMenu.beginning, containsString("HOY - Miércoles - Mediodía en Cocina Soho!!"))
        assertThat(parsedMenu.beginning, containsString("Con estas propuestas:"))
        assertThat(parsedMenu.beginning, not(containsString(parsedMenu.menus[0])))

        assertThat(parsedMenu.ending, `is`(""))
    }

    @Test
    fun it_parses_case3() {
        val parsedMenu = DailyMenus.from(MENU_POST_3)

        assertThat(parsedMenu.menus.size, `is`(6))
        parsedMenu.menus.forEach { assertThat(it, containsString("$")) }

        assertThat(parsedMenu.beginning, containsString("HOY - Martes - Mediodía en Cocina Soho!!"))
        assertThat(parsedMenu.beginning, containsString("Para este mediodia te propongo:"))

        assertThat(parsedMenu.ending, containsString("PARA CONSULTAS y PEDIDOS"))
        assertThat(parsedMenu.ending, containsString("Otro final"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun it_checks_for_invalid_menus() {
        DailyMenus.from(NOT_MENU_POST_1).menus
    }
}
