package com.example.toutiaoapplication.ui.login

import android.os.Looper
import android.os.Handler
import android.util.Log
import com.example.toutiaoapplication.repo.ApiServers
import com.example.toutiaoapplication.repo.entities.LoginPayload
import com.example.toutiaoapplication.repo.entities.User
import com.example.toutiaoapplication.utils.isValid
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "LoginPresenter"

class LoginPresenter(val view: LoginContract.View) : LoginContract.Presenter {

    override fun login(userName: String, password: String) {
        if (userName.isValid() and password.isValid()) {
            val payload = LoginPayload(userName, password)
            uiThread { testLogin(payload) }
        } else view.showInputError()
    }

    private fun testLogin(payload: LoginPayload) {
        ApiServers().getApiService().loginUser(payload)
            .enqueue(object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d(TAG, "on failure $t")
                    uiThread { view.onLoginFailed() }
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    Log.d(TAG, "on response: $response")
                    response.body()?.let {
                        Log.d(TAG, "返回: $it")
                        when (it.ret_code) {
                            "-1" -> uiThread { view.errorInfo() }
                            "0" -> uiThread { view.onLoginSuccess(it.data.username) }
                            else -> Log.d(TAG, it.ret_code)
                        }
                    }
                }
            })
    }

    companion object {
        val handler by lazy {
            Handler(Looper.getMainLooper())
        }
    }

    private fun uiThread(f: () -> Unit) {
        handler.post{ f() }
    }

    override fun start() {
        TODO("Not yet implemented")
    }
}