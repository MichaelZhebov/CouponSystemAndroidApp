package com.couponssystem.adminappforcs.ui.main.view.coupon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.couponssystem.adminappforcs.R
import com.couponssystem.adminappforcs.data.api.ApiHelper
import com.couponssystem.adminappforcs.data.api.NetworkService
import com.couponssystem.adminappforcs.databinding.CouponDetailsFragmentBinding
import com.couponssystem.adminappforcs.ui.base.ViewModelFactory
import com.couponssystem.adminappforcs.ui.main.viewmodel.coupon.CouponDetailsViewModel
import kotlinx.android.synthetic.main.coupon_details_fragment.*

class CouponDetailsFragment : Fragment() {

    private lateinit var viewModel: CouponDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Coupon Details"
        val binding : CouponDetailsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.coupon_details_fragment, container, false)
        val id : Long = arguments?.let { CouponDetailsFragmentArgs.fromBundle(
            it
        ).id}!!
        viewModel = ViewModelProviders.of(this, ViewModelFactory(ApiHelper(NetworkService.retrofitService()), id)).get(
            CouponDetailsViewModel::class.java)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Glide.with(this).load(viewModel.coupon.image).into(image)
    }
}