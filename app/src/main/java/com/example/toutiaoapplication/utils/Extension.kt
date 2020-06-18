package com.example.toutiaoapplication.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.toutiaoapplication.repo.entities.User

fun Context.toast(text: String, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, text, duration).show()
}

fun Context.toast(resId: Int, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, resId, duration).show()
}

fun Fragment.toast(message: String) {
    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(resId: Int) {
    Toast.makeText(activity, resId, Toast.LENGTH_SHORT).show()
}

fun String.isValid(): Boolean = this.matches(Regex("^.{4,20}$")) // 长度为3到20的所有字符 $?

fun saveUserInfo(context: Context, user: User) {
    val sp = context.getSharedPreferences("user_config", MODE_PRIVATE)
    sp.edit().apply {
        putInt("aid", user.aid)
        putString("udesc", user.descriptor)
        putInt("uid", user.uid)
        putString("umail", user.email)
        putString("uname", user.username)
        putInt("ustat", user.ban)
        putLong("utime", user.register_day)
        putBoolean("save_flag", true)
        apply()
    }
}

fun isAlreadyLogged(context: Context): Boolean {
    val sp = context.getSharedPreferences("user_config", MODE_PRIVATE)
    return sp.getBoolean("save_flag", false)
}

fun loadSavedUserInfo(context: Context): User {
    val sp = context.getSharedPreferences("user_config", MODE_PRIVATE)
    return User(
        aid = sp.getInt("aid", -1),
        descriptor = sp.getString("udesc", "")!!,
        uid = sp.getInt("uid", -1),
        email = sp.getString("umail", "")!!,
        username = sp.getString("uname", "")!!,
        ban = sp.getInt("ustat", -1),
        register_day = sp.getLong("utime", -1)
    )
}

fun clearUserInfo(context: Context) {
    val sp = context.getSharedPreferences("user_config", MODE_PRIVATE)
    sp.edit().apply{
        remove("aid")
        remove("udesc")
        remove("uid")
        remove("umail")
        remove("uname")
        remove("ustat")
        remove("utime")
        putBoolean("save_flag", false)
        apply()
    }
}