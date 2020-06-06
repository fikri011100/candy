package binus.bmcc.candy.feature.quiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import binus.bmcc.candy.R
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    private var questionArr = intArrayOf(R.string.question_1, R.string.question_2, R.string.question_3, R.string.question_4)
    private var answerArr1 = arrayOf("Jajaghu", "Baghogo", "Adudu")
    private var answerArr2 = arrayOf("Abad ke-3 Masehi", "Abad ke-2 Masehi", "Abad ke-1 Masehi")
    private var answerArr3 = arrayOf("DKI Jakarta", "Jawa Timur", "Jawa Tengah")
    private var answerArr4 = arrayOf("Desa Kupang", "Desa Tumpang", "Desa Singasari")
    private var answerIdArr = intArrayOf(0,0,1,1)
    private  var index: Int = 0
    private  var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        setQuestion()
        button_next_quiz.setOnClickListener {
            if (index == 4) {
                val intent = Intent(applicationContext, QuizResultActivity::class.java)
                intent.putExtra("score", score.toString())
                startActivity(intent)
                finish()
            } else {
                setQuestion()
            }
        }
    }

    private fun setQuestion() {
        img_answer1.visibility = View.INVISIBLE
        img_answer2.visibility = View.INVISIBLE
        img_answer3.visibility = View.INVISIBLE
        text_question.setText(questionArr.get(index))
        textview_question_countdown.setText((index + 1).toString())
        if (index == 0) {
            text_answer1.setText(answerArr1.get(0))
            text_answer2.setText(answerArr1.get(1))
            text_answer3.setText(answerArr1.get(2))
        } else if (index == 1) {
            text_answer1.setText(answerArr2.get(0))
            text_answer2.setText(answerArr2.get(1))
            text_answer3.setText(answerArr2.get(2))
        } else if (index == 2) {
            text_answer1.setText(answerArr3.get(0))
            text_answer2.setText(answerArr3.get(1))
            text_answer3.setText(answerArr3.get(2))
        } else if (index == 3) {
            text_answer1.setText(answerArr4.get(0))
            text_answer2.setText(answerArr4.get(1))
            text_answer3.setText(answerArr4.get(2))
        }
        cons_answer1.setOnClickListener {
            img_answer1.visibility = View.VISIBLE
            img_answer2.visibility = View.INVISIBLE
            img_answer3.visibility = View.INVISIBLE
            if (index != 4) {
                if (answerIdArr.get(index) == 0) {
                    score++
                }
            }
        }
        cons_answer2.setOnClickListener {
            img_answer1.visibility = View.INVISIBLE
            img_answer2.visibility = View.VISIBLE
            img_answer3.visibility = View.INVISIBLE
            if (index != 4) {
                if (answerIdArr.get(index) == 1) {
                    score++
                }
            }
        }
        cons_answer3.setOnClickListener {
            img_answer1.visibility = View.INVISIBLE
            img_answer2.visibility = View.INVISIBLE
            img_answer3.visibility = View.VISIBLE
            if (index != 4) {
                if (answerIdArr.get(index) == 2) {
                    score++
                }
            }
        }
        index++
    }
}
