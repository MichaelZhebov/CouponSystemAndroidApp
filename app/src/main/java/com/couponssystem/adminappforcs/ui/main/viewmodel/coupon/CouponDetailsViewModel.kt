package com.couponssystem.adminappforcs.ui.main.viewmodel.coupon

import androidx.lifecycle.ViewModel
import com.couponssystem.adminappforcs.data.repository.MainRepository
import kotlinx.coroutines.runBlocking

class CouponDetailsViewModel(private val mainRepository: MainRepository, private val id : Long) : ViewModel() {

    val coupon = runBlocking {
        mainRepository.getCoupon(id)
    }

}