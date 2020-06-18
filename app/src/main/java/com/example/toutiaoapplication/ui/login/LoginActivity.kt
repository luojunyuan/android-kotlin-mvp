package com.example.toutiaoapplication.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.utils.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {
    override fun onLoginSuccess() {
        toast("登入成功")
        finish()
    }

    override fun onLoginFailed() {
        toast("登陆错误")
    }

    override fun showInputError() {
        toast("用户名或密码输入不规范")
    }

    override lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this)
        initView()
    }

    private fun initView() {
        login.setOnClickListener {
            val userName = username.text.trim().toString()
            val password = password.text.trim().toString()
            presenter.login(userName, password)
        }
    }
}