package org.chechtalks.lunchbot.bot.utils

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.expect

class ParameterParserSpec : Spek({

    given("a parameters parser") {
        val parser = ParameterParser()

        given("a parameter") {
            val parameters = listOf("deep", "size", "weight")

            parameters.forEach {
                given("a command") {
                    val scenarios = mapOf(
                            "$it=1" to 1,
                            "sarlanga $it=10" to 10,
                            "abc$it=999dsd" to 999,
                            "command $it=123.4" to 123,
                            "$it=1 dep=2" to 1,
                            "$it=1 $it=2" to 2,
                            "$it=1 $it=2 deep=3" to 3,
                            "dep=1" to null,
                            "no $it" to null,
                            "$it is 2" to null,
                            "$it=one" to null)

                    scenarios.forEach { command, value ->
                        on("parsing int value from $command") {
                            val result = parser.parseInt(it, command)

                            it("parses $value") {
                                expect(value) { result }
                            }
                        }
                    }
                }
            }
        }
    }
})

