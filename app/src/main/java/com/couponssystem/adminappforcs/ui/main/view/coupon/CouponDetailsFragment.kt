package com.couponssystem.adminappforcs.ui.main.view.coupon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.couponssystem.adminappforcs.R
import com.couponssystem.adminappforcs.data.api.ApiHelper
import com.couponssystem.adminappforcs.data.api.NetworkService
import com.couponssystem.adminappforcs.ui.base.ViewModelFactory
import com.couponssystem.adminappforcs.ui.main.viewmodel.coupon.CouponDetailsViewModel
import com.couponssystem.adminappforcs.utils.Status
import kotlinx.android.synthetic.main.coupon_details_fragment.*

class CouponDetailsFragment : Fragment() {

    private lateinit var viewModel: CouponDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.coupon_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
        setupObservers()
    }

    private fun setupViewModel() {
        (activity as AppCompatActivity).supportActionBar?.title = "Coupon Details"
        val id: Long = arguments?.let {
            CouponDetailsFragmentArgs.fromBundle(
                it
            ).id
        }!!
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(NetworkService.retrofitService()), id)
        ).get(
            CouponDetailsViewModel::class.java
        )
    }

    private fun setupObservers() {
        viewModel.getCoupon().observe(this.viewLifecycleOwner, Observer {
            it?.let { resourse ->
                when (resourse.status) {
                    Status.SUCCESS -> {
                        Glide.with(this).load(resourse.data?.image).into(image)
                        couponId.text = resourse.data?.id.toString()
                        couponCompanyName.text = resourse.data?.companyName
                        couponCompanyId.text = resourse.data?.companyId.toString()
                        couponCategory.text = resourse.data?.category
                        couponTitle.text = resourse.data?.title
                        couponDescription.text = resourse.data?.description
                        couponAmount.text = resourse.data?.amount.toString()
                        couponPrice.text = resourse.data?.price.toString()
                        couponStartDate.text = resourse.data?.startDate
                        couponEndDate.text = resourse.data?.endDate
                        changeVisibility()
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

    private fun changeVisibility() {
        progressBarCouponDetails.visibility = View.GONE
    }

}