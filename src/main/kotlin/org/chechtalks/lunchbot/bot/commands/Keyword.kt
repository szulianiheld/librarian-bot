package org.chechtalks.lunchbot.bot.commands

class Keyword(vararg val synonymous: String) {

    fun isPresent(command: String, ignoreCase: Boolean = true) = synonymous.any { command.contains(it, ignoreCase) }

}

val MENU = Keyword("menu", "menú")
val COCINA_SOHO = Keyword("soho", "cocina soho")
val AL_PUNTO_JUSTO = Keyword("al punto", "al punto justo", "alpunto", "alpuntojusto")
val LO_DE_ROSA = Keyword("lo de rosa", "loderosa", "lo de rosita")
val MAFALDA = Keyword("mafalda")
val AYUDA = Keyword("ayuda", "help")
val POSTS = Keyword("posts", "posteos", "mensajes")
val CONTEO = Keyword("conteo", "recuento", "suma", "total", "conta", "contá")
val PEDIDOS = Keyword("pedidos", "ordenes", "órdenes", "menu", "menú")
