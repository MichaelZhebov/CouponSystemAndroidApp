package com.couponssystem.adminappforcs.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.couponssystem.adminappforcs.R
import com.couponssystem.adminappforcs.data.api.ApiHelper
import com.couponssystem.adminappforcs.data.api.NetworkService
import com.couponssystem.adminappforcs.data.model.AuthBody
import com.couponssystem.adminappforcs.ui.base.ViewModelFactory
import com.couponssystem.adminappforcs.ui.main.viewmodel.LoginViewModel
import com.couponssystem.adminappforcs.utils.LoggedInUser
import com.couponssystem.adminappforcs.utils.Status
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(ApiHelper(NetworkService.retrofitService()))
        ).get(LoginViewModel::class.java)
    }

    private fun setupObservers() {
        val authBody = AuthBody(txtEmail.text.toString(), txtPwd.text.toString())
        viewModel.login(authBody).observe(this, Observer {
            it?.let { resourse ->
                when (resourse.status) {
                    Status.SUCCESS -> {
                        pBarLogin.visibility = View.GONE
                        resourse.data?.let { response -> LoggedInUser.token = response.accessToken }
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                    Status.ERROR -> {
                        pBarLogin.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        pBarLogin.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    fun getAuth(view: View) {
        setupObservers()
    }
}