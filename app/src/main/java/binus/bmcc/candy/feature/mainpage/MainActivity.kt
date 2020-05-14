package binus.bmcc.candy.feature.mainpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import binus.bmcc.candy.R
import binus.bmcc.candy.feature.chatbot.IntroChatbot
import binus.bmcc.candy.feature.login.LoginActivity
import binus.bmcc.candy.feature.quiz.IntroQuiz
import binus.bmcc.candy.utils.SharedPref
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mxn.soul.flowingdrawer_core.ElasticDrawer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.flowing_menu.*

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPref
    lateinit var usrName: String
    private lateinit var auth: FirebaseAuth
    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPref = SharedPref(this)
        auth = FirebaseAuth.getInstance()
        setupDrawer()
        usrName = sharedPref.getEmail("username", "nama")!!
        name.setText(usrName)
        email.setText(auth.currentUser?.email)
        text_name.setText(usrName)
        card_chatbot.setOnClickListener {
            val intent = Intent(applicationContext, IntroChatbot::class.java)
            startActivity(intent)
        }
        signOutMenu.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            sharedPref.clearSharedPreference()
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        card_quiz.setOnClickListener {
            val intent = Intent(applicationContext, IntroQuiz::class.java)
            startActivity(intent)
        }
    }

    private fun setupDrawer() {
        drawerlayout.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL)
        drawerlayout.setOnDrawerStateChangeListener(object : ElasticDrawer.OnDrawerStateChangeListener {
            override fun onDrawerStateChange(oldState: Int, newState: Int) {
                if (newState == ElasticDrawer.STATE_CLOSED) {
                }
            }

            override fun onDrawerSlide(openRatio: Float, offsetPixels: Int) {
            }
        })

        img_nav_left.setOnClickListener {
            drawerlayout.openMenu()

        }
    }
}
