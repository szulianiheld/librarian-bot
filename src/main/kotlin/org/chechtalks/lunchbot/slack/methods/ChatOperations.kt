package org.chechtalks.lunchbot.slack.methods

import com.github.seratch.jslack.api.methods.MethodsClient
import com.github.seratch.jslack.api.methods.request.chat.ChatPostMessageRequest
import com.github.seratch.jslack.api.model.Message
import org.springframework.stereotype.Component

@Component
class ChatOperations(
        private val slackMethodsApi: MethodsClient,
        private val slackToken: String) {

    fun postMessage(channel: String, text: String, threadTs: String? = null): Message {
        val request = ChatPostMessageRequest.builder()
                .token(slackToken)
                .asUser(true)
                .channel(channel)
                .text(text)
                .threadTs(threadTs)
                .build()

        val response = slackMethodsApi.chatPostMessage(request)

        if (!response.isOk)
            throw SlackMethodsApiException("Got this error response calling chat.postMessage: ${response.error}")

        return response.message
    }

    fun postThreadedMessages(channel: String, text: String, vararg children: String) {
        val mainMessage = postMessage(channel, text)

        children.forEach { postMessage(channel, it, mainMessage.ts) }
    }
}
