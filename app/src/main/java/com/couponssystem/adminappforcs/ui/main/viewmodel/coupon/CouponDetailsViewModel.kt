package com.couponssystem.adminappforcs.ui.main.viewmodel.coupon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.couponssystem.adminappforcs.data.repository.MainRepository
import com.couponssystem.adminappforcs.utils.Resource
import kotlinx.coroutines.Dispatchers

class CouponDetailsViewModel(private val mainRepository: MainRepository, private val id : Long) : ViewModel() {

    fun getCoupon() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getCoupon(id)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}