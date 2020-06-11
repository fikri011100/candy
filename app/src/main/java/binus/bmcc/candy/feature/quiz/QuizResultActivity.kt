package binus.bmcc.candy.feature.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.IntegerRes
import binus.bmcc.candy.R
import binus.bmcc.candy.feature.mainpage.MainActivity
import binus.bmcc.candy.utils.SharedPref
import kotlinx.android.synthetic.main.activity_quiz_result.*
import java.lang.Exception

class QuizResultActivity : AppCompatActivity() {

    lateinit var usrName: String
    private lateinit var sharedPref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_result)
        sharedPref = SharedPref(this)
        usrName = sharedPref.getEmail("username", "nama")!!
        try {
            val ss:String = intent.getStringExtra("score")
            textview_score.setText(ss)
            text_current.setText("Congratulations!")
            text_high_score.setText("New High Score")
            Log.d("score", ss)
        } catch (e: Exception) {

        }
        edt_nickname.setText(usrName)
        cons_start.setOnClickListener {
            val intent = Intent(applicationContext, QuizActivity::class.java)
            startActivity(intent)
            finish()
        }
        cons_back.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
