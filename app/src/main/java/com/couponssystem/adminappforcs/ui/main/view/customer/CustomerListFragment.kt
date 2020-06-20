package com.couponssystem.adminappforcs.ui.main.view.customer

import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.couponssystem.adminappforcs.R
import com.couponssystem.adminappforcs.data.api.ApiHelper
import com.couponssystem.adminappforcs.data.api.NetworkService
import com.couponssystem.adminappforcs.data.model.User
import com.couponssystem.adminappforcs.ui.base.ViewModelFactory
import com.couponssystem.adminappforcs.ui.main.adapter.CustomerRecyclerAdapter
import com.couponssystem.adminappforcs.ui.main.viewmodel.customer.CustomerListViewModel
import com.couponssystem.adminappforcs.utils.Status
import kotlinx.android.synthetic.main.company_list_fragment.*

class CustomerListFragment : Fragment() {

    private lateinit var viewModel: CustomerListViewModel
    private lateinit var adapter : CustomerRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.customer_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Customers List"
        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(ApiHelper(NetworkService.retrofitService()))
        ).get(CustomerListViewModel::class.java)
        this.context?.let {
            ContextCompat.getColor(
                it, R.color.colorPrimary)
        }?.let { swipeRefreshLayout.setProgressBackgroundColorSchemeColor(it) }
        swipeRefreshLayout.setColorSchemeColors(Color.WHITE)
        swipeRefreshLayout.setOnRefreshListener {
            setupObservers()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter = CustomerRecyclerAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getCustomers().observe(this.viewLifecycleOwner, Observer {
            it?.let { resourse ->
                when (resourse.status) {
                    Status.SUCCESS -> {
                        changeVisibility()
                        resourse.data?.let { users -> retrieveList(users) }
                    }
                    Status.ERROR -> {
                        changeVisibility()
                        Toast.makeText(this.context, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        changeVisibility()
                    }
                }
            }
        })
    }

    private fun retrieveList(users: List<User>) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }

    private fun changeVisibility() {
        progressBar.visibility = View.GONE
    }

}