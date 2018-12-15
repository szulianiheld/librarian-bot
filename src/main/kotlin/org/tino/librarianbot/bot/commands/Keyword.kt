package org.tino.librarianbot.bot.commands

class Keyword( vararg val synonymous: String) {

    fun isPresent(command: String, ignoreCase: Boolean = true) = synonymous.any { command.contains(it, ignoreCase) }

}

val HELP = Keyword("ayuda", "help")
