package com.couponssystem.adminappforcs.ui.main.viewmodel.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.couponssystem.adminappforcs.data.repository.MainRepository
import com.couponssystem.adminappforcs.utils.Resourse
import kotlinx.coroutines.Dispatchers

class CustomerListViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getCustomers() = liveData(Dispatchers.IO) {
        emit(Resourse.loading(data = null))
        try {
            emit(Resourse.success(data = mainRepository.getCustomers()))
        } catch (exception : Exception) {
            emit(Resourse.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}