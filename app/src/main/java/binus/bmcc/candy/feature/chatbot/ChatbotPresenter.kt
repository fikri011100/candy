package binus.bmcc.candy.feature.chatbot

import ai.api.AIDataService
import ai.api.model.AIRequest
import ai.api.model.AIResponse
import android.util.Log
import binus.bmcc.candy.entity.ChatMessage
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.*

class ChatbotPresenter(val aiDataAIService: AIDataService,
                       val ref: DatabaseReference) : ChatContract.Presenter {
    val aiRequest = AIRequest()

    val job = Job()

    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)

    override fun sendMessage(message: String) {
        val chatMessage = ChatMessage(message, "user")
        ref.child("chat").push().setValue(chatMessage)
        aiRequest.setQuery(message)


        val response: Deferred<AIResponse>? = coroutineScope.async {
            aiDataAIService.request(aiRequest)
        }

        coroutineScope.launch {
            sendMessageToServer(response?.await())
        }

    }

    private fun sendMessageToServer(response: AIResponse?) {
        if (response != null) {
            val result = response?.result?.fulfillment?.speech
            val chatMessage = ChatMessage(result, "bot")
            ref.child("chat").push().setValue(chatMessage)
            Log.d("testing bot", result)
        } else {
            Log.d("testing bot", "null")
        }
    }

    override fun onDestroy() {
        job.cancel()
    }
}