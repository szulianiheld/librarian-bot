package org.chechtalks.lunchbot.bot.utils

import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.startsWith
import org.chechtalks.lunchbot.constants.ALPUNTO_MENU_VALID
import org.chechtalks.lunchbot.constants.SOHO_MENU_VALID
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.expect

class MenuParserSpec : Spek({

    given("a menu parser") {

        on("parsing soho menu") {
            val result = MenuParser.parseSoho(SOHO_MENU_VALID)
            it("parses all lines") {
                expect(135) { result.size }
                expect("LENGUADO a la PIZZA con tomate fresco, oregano, morrón asado, aceitunas y Papas Especiadas") {result[40]}
            }
        }

        on("parsing al punto justo menu") {
            val result = MenuParser.parseSoho(ALPUNTO_MENU_VALID)
            it("parses all menus") {
                expect(17) { result.size }
                expect("- HAMBURGUESAS VEGETARIANAS (con tomate y queso fundido) CON ENSALADA ( Mix de hojas verdes frescas, calabazas asadas, tomate cherry y aderezo de la casa ).") {result[8]}
            }
        }

        on("parsing lo de rosa menu") {
            val jsonMenus = "/menu/lo-de-rosa.json"
            val rosaMenus = this.javaClass.getResource(jsonMenus)
            val result = MenuParser.parse(rosaMenus)
            it("parses all menus") {
                expect(36) { result.size }
                expect("Roquefort (Muzzarella y roquefort)") { result[32] }
            }
        }
    }
})