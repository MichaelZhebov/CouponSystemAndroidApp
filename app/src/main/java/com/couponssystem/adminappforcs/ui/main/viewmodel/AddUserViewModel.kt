package com.couponssystem.adminappforcs.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.couponssystem.adminappforcs.data.model.User
import com.couponssystem.adminappforcs.data.repository.MainRepository
import com.couponssystem.adminappforcs.utils.Resource
import kotlinx.coroutines.Dispatchers

class AddUserViewModel(private val mainRepository: MainRepository) : ViewModel() {

    var user = User()

    fun addUser() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.addUser(user)))
        } catch (exception : Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}