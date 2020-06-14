package com.couponssystem.adminappforcs.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.couponssystem.adminappforcs.R
import com.couponssystem.adminappforcs.data.api.ApiHelper
import com.couponssystem.adminappforcs.data.api.NetworkService
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
        return inflater.inflate(R.layout.add_user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupViewModel() {
        (activity as AppCompatActivity).supportActionBar?.title = "Add User"
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(ApiHelper(NetworkService.retrofitService()))
        ).get(AddUserViewModel::class.java)
    }

    private fun setupUI() {
        chooseRole.adapter = activity?.applicationContext?.let { ArrayAdapter(it, R.layout.drop_down_item, roles) }
        chooseRole.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
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

    private fun setupObservers() {
    }

}