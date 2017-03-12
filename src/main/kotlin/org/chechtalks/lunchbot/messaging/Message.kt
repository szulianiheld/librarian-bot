package org.chechtalks.lunchbot.messaging

class Message(val message: String) {

    val lowercaseMessage = message.toLowerCase()

    fun contains(vararg words: String) = words.find { !presentInMessage(it) } == null

    private fun presentInMessage(it: String) = lowercaseMessage.contains(it.toLowerCase())
}