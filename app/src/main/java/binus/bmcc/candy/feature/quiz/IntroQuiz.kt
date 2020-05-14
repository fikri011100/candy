package binus.bmcc.candy.feature.quiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import binus.bmcc.candy.R
import kotlinx.android.synthetic.main.activity_intro_quiz.*

class IntroQuiz : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_quiz)
        button_gotochat.setOnClickListener {
            val intent = Intent(applicationContext, QuizResultActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
