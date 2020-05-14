package binus.bmcc.candy.feature.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import binus.bmcc.candy.R
import binus.bmcc.candy.entity.User
import binus.bmcc.candy.feature.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance().reference
        button_register.setOnClickListener {
            createUser(edt_email.text.toString(), edt_password.text.toString(), edt_username.text.toString(), edt_password_confirm.text.toString())
        }
        textview_signin.setOnClickListener{
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createUser(email: String, password: String, username: String, passwordConfirm: String) {
        if (password.equals(passwordConfirm)) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    onAuthSuccess(it.result!!.user!!, username)
                    Toast.makeText(
                        this@RegisterActivity,
                        "Register berhasil, silahkan login terlebih dahulu",
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Register gagal, silahkan cek internet anda kembali",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        } else {
            Toast.makeText(this@RegisterActivity, "Password Konfirmasi tidak valid", Toast.LENGTH_LONG).show()
        }
    }

    //create user in firebase database
    private fun onAuthSuccess(user: FirebaseUser, username: String) {
        var model: User = User(username, user.email)
        db.child("user").child(user.uid).setValue(model)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}
