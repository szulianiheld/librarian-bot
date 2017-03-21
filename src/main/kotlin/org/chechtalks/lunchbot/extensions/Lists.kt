package org.chechtalks.lunchbot.extensions

fun List<String>.firstContaining(vararg words: String, ignoreCase: Boolean = true) = this.find { it.contains(*words, ignoreCase = ignoreCase) }

fun List<String>.preformatted() = this.joinToString(separator = "```\n```", prefix = "```", postfix = "```")

fun List<String>.humanToString(): String {
    return when {
        this.isEmpty() -> ""
        else -> this.reduce { x, y -> "$x\n$y" }
    }
}
