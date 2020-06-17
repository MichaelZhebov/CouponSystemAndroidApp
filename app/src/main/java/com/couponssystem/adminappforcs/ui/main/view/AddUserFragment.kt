package com.couponssystem.adminappforcs.ui.main.view

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.couponssystem.adminappforcs.R
import com.couponssystem.adminappforcs.data.api.ApiHelper
import com.couponssystem.adminappforcs.data.api.NetworkService
import com.couponssystem.adminappforcs.databinding.AddUserFragmentBinding
import com.couponssystem.adminappforcs.ui.base.ViewModelFactory
import com.couponssystem.adminappforcs.ui.main.viewmodel.AddUserViewModel
import kotlinx.android.synthetic.main.add_user_fragment.*

class AddUserFragment : Fragment() {

    private lateinit var viewModel: AddUserViewModel
    private val roles = arrayListOf("Company", "Customer")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Add User"
        val binding: AddUserFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.add_user_fragment, container, false)
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(NetworkService.retrofitService()))
        ).get(AddUserViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUI()
        addUser.setOnClickListener(View.OnClickListener {
            if (checkDataEntered()) {
                addUser()
                Toast.makeText(
                    this.context,
                    "User Added", Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun addUser() {
        viewModel.addUser()
    }

    private fun checkDataEntered(): Boolean {
        var checked = true
        if (TextUtils.isEmpty(userName.text)) {
            userName.setError("Full Name is required")
            checked = false
        }
        if (TextUtils.isEmpty(userEmail.text)) {
            userEmail.setError("Email is required")
            checked = false
        }
        if (!TextUtils.isEmpty(userEmail.text) && !Patterns.EMAIL_ADDRESS.matcher(userEmail.text)
                .matches()
        ) {
            userEmail.setError("Enter valid email")
            checked = false
        }
        if (TextUtils.isEmpty(userPass.text)) {
            userPass.setError("Password is required")
            checked = false
        }
        if (!TextUtils.isEmpty(userPass.text) && userPass.text?.length!! < 4) {
            userPass.setError("Password length must be more than 4 characters")
            checked = false
        }
        return checked
    }

    private fun setupUI() {
        chooseRole.adapter =
            activity?.applicationContext?.let { ArrayAdapter(it, R.layout.drop_down_item, roles) }
        chooseRole.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val role = parent?.getItemAtPosition(position).toString()
                if (role == "Company") {
                    viewModel.user.role = "ROLE_COMPANY"
                } else if (role == "Customer") {
                    viewModel.user.role = "ROLE_CUSTOMER"
                }
            }
        }
    }

}