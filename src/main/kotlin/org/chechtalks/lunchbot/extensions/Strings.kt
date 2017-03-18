package org.chechtalks.lunchbot.extensions

fun String.contains(vararg words: String, ignoreCase: Boolean = true) = words.find { !this.contains(it, ignoreCase) } == null

fun String.dropFirstNonLetters() = this.dropWhile { !it.isLetter() }

fun String.preformatted() = "```$this```"

fun String.quoted() = "> $this"