package binus.bmcc.candy.feature.chatbot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import binus.bmcc.candy.R
import kotlinx.android.synthetic.main.activity_intro_chatbot.*

class IntroChatbot : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_chatbot)
        button_gotochat.setOnClickListener {
            val intent = Intent(applicationContext, ChatbotActivity::class.java)
            startActivity(intent)
        }
    }
}
