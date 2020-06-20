package com.couponssystem.adminappforcs.ui.main.view.coupon

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
import com.couponssystem.adminappforcs.data.model.Coupon
import com.couponssystem.adminappforcs.ui.base.ViewModelFactory
import com.couponssystem.adminappforcs.ui.main.adapter.CouponRecyclerAdapter
import com.couponssystem.adminappforcs.ui.main.viewmodel.coupon.CouponListViewModel
import com.couponssystem.adminappforcs.utils.Status
import kotlinx.android.synthetic.main.company_list_fragment.*

class CouponListFragment : Fragment() {

    private lateinit var viewModel: CouponListViewModel
    private lateinit var adapter : CouponRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.coupon_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Coupons List"
        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(ApiHelper(NetworkService.retrofitService()))
        ).get(CouponListViewModel::class.java)
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
        adapter = CouponRecyclerAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getCoupons().observe(this.viewLifecycleOwner, Observer {
            it?.let { resourse ->
                when (resourse.status) {
                    Status.SUCCESS -> {
                        changeVisibility()
                        resourse.data?.let { coupons -> retrieveList(coupons) }
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

    private fun retrieveList(coupons: List<Coupon>) {
        adapter.apply {
            addCoupon(coupons)
            notifyDataSetChanged()
        }
    }

    private fun changeVisibility() {
        progressBar.visibility = View.GONE
    }
}