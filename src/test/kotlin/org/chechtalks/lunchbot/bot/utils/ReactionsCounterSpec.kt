package org.chechtalks.lunchbot.bot.utils

import com.github.seratch.jslack.api.model.Reaction
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.expect

class ReactionsCounterSpec : Spek({

    given("a reactions counter") {
        val counter = ReactionsCounter()

        on("counting single reactions") {
            val reactions = mapOf(
                    "one" to 1,
                    "two" to 1,
                    "three" to 1,
                    "four" to 1,
                    "seven" to 1)
                    .map { aReaction(it.key, it.value) }

            val result = counter.count(reactions)
            it("accumulates the total") {
                expect(1 + 2 + 3 + 4 + 7) { result }
            }
        }

        on("counting multiple reactions") {
            val reactions = mapOf(
                    "one" to 1,
                    "heavy_plus_sign" to 2,
                    "five" to 3,
                    "eight" to 2)
                    .map { aReaction(it.key, it.value) }

            val result = counter.count(reactions)
            it("accumulates the total") {
                expect(1 + 1 * 2 + 5 * 3 + 8 * 2) { result }
            }
        }

        on("counting some invalid reactions") {
            val reactions = mapOf(
                    "nine" to 1,
                    "keycap_ten" to 1,
                    "six" to 3,
                    "fake" to 1,
                    "twwo" to 3)
                    .map { aReaction(it.key, it.value) }

            val result = counter.count(reactions)
            it("ignores them") {
                expect(9 + 10 + 6 * 3) { result }
            }
        }

        on("counting all invalid reactions") {
            val reactions = mapOf(
                    "invalid" to 1,
                    "dog" to 2)
                    .map { aReaction(it.key, it.value) }

            val result = counter.count(reactions)
            it("doesn't count anything") {
                expect(0) { result }
            }
        }
    }
})

fun aReaction(name: String, count: Int) = Reaction.builder()
        .name(name)
        .count(count)
        .build()!!
