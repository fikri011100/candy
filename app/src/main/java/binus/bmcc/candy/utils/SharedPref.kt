package binus.bmcc.candy.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPref(val context: Context) {
    private val PREF_NAME = "candy"
    val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun isLogin(KEYNAME: String, status: Boolean) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean(KEYNAME, status!!)
        editor.commit()
    }

    fun setUsername(KEYNAME: String, username: String?) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEYNAME, username!!)
        editor.commit()
    }

    fun setEmail(KEYNAME: String, email: String?) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEYNAME, email!!)
        editor.commit()
    }

    fun getEmail(KEYNAME: String, defaultValue: String): String? {
        return sharedPref.getString(KEYNAME, defaultValue)
    }

    fun getUsername(KEYNAME: String, defaultValue: String): String? {
        return sharedPref.getString(KEYNAME, defaultValue)
    }

    fun getLogin(KEY_NAME: String, defaultValue: Boolean): Boolean {

        return sharedPref.getBoolean(KEY_NAME, defaultValue)
    }

    fun clearSharedPreference() {

        val editor: SharedPreferences.Editor = sharedPref.edit()

        //sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

        editor.clear()
        editor.commit()
    }
}