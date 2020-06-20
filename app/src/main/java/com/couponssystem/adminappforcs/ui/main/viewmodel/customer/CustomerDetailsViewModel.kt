package com.couponssystem.adminappforcs.ui.main.viewmodel.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.couponssystem.adminappforcs.data.model.User
import com.couponssystem.adminappforcs.data.repository.MainRepository
import com.couponssystem.adminappforcs.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class CustomerDetailsViewModel(private val mainRepository: MainRepository, private val id : Long) : ViewModel() {

    var user = User()

    fun getCustomer() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getCustomer(id)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun deleteCustomer() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.deleteCustomer(id)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun updateCustomer() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.updateCustomer(id, user = user)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}