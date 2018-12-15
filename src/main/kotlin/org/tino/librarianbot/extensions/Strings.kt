package org.tino.librarianbot.extensions

import org.tino.librarianbot.bot.commands.Keyword

fun String.contains(vararg words: Keyword, ignoreCase: Boolean = true) = words.none { !it.isPresent(this, ignoreCase) }

const val DOUBLE_JUMP = "\n\n"