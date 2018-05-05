package org.chechtalks.lunchbot.bot.utils

import org.chechtalks.lunchbot.constants.ALPUNTO_MENU_INVALID
import org.chechtalks.lunchbot.constants.ALPUNTO_MENU_VALID
import org.chechtalks.lunchbot.constants.SOHO_MENU_INVALID
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
                expect(58) { result.size }
            }
        }

        on("parsing al punto justo menu") {
            val result = MenuParser.parseSoho(ALPUNTO_MENU_VALID)
            it("parses all menus") {
                expect(9) { result.size }
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

        on("validating soho menu") {
            val result = MenuParser.isValidSohoMenu(SOHO_MENU_INVALID)
            it("detects invalid menus") {
                expect(false) { result }
            }
        }

        on("validating al punto justo menu") {
            val result = MenuParser.isValidAlPuntoMenu(ALPUNTO_MENU_INVALID)
            it("detects invalid menus") {
                expect(false) { result }
            }
        }
    }
})