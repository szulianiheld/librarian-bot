package org.chechtalks.lunchbot.extensions

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking

fun List<String>.firstContaining(vararg words: String, ignoreCase: Boolean = true) = this.find { it.contains(*words, ignoreCase = ignoreCase) }

fun List<String>.preformatted() = this.map(String::preformatted)

fun List<String>.quoted() = this.map(String::quoted)

fun List<String>.humanToString(): String {
    return when {
        this.isEmpty() -> ""
        else -> this.reduce { x, y -> "$x\n$y" }
    }
}

fun <A, B> List<A>.pForEach(f: suspend (A) -> B) = runBlocking {
    map { async(CommonPool) { f(it) } }.forEach { it.await() }
}