package org.chechtalks.lunchbot.extensions

import java.time.LocalDate
import java.time.ZoneId
import java.util.*

fun Date.toLocalDate(): LocalDate = this.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

fun String.contains(vararg words: String, ignoreCase: Boolean = true) = words.find { !this.contains(it, ignoreCase) } == null

fun List<String>.firstContaining(vararg words: String, ignoreCase: Boolean = true) = this.find { it.contains(*words, ignoreCase = ignoreCase) }
