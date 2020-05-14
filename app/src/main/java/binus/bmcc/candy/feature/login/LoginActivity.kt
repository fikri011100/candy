package binus.bmcc.candy.feature.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import binus.bmcc.candy.R
import binus.bmcc.candy.entity.User
import binus.bmcc.candy.feature.mainpage.MainActivity
import binus.bmcc.candy.feature.register.RegisterActivity
import binus.bmcc.candy.utils.SharedPref
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: DatabaseReference
    private lateinit var sharedPreferences: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        sharedPreferences = SharedPref(this)
        auth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance().reference
        button_login.setOnClickListener {
            login(edt_username.text.toString(), edt_password.text.toString())
        }
        textview_signup.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this@LoginActivity, "Email atau passwod harus diisi", Toast.LENGTH_LONG).show()
        } else {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                Log.d("user", it.isSuccessful.toString())
                if (it.isSuccessful) {
                    db.child("user").orderByChild("email").equalTo(email).addValueEventListener(object :ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {

                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            for (noteDataSnapshot : DataSnapshot in p0.children) {
                                val usr: User? = noteDataSnapshot.getValue(User::class.java)
                                sharedPreferences.setUsername("username", usr!!.username)
                            }
                        }

                    })
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginActivity, "Username atau password salah", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}
