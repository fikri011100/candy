package binus.bmcc.candy.feature.chatbot

interface ChatContract {
    interface View{

    }

    interface Presenter{
        fun sendMessage(message: String)
        fun onDestroy()
    }
}