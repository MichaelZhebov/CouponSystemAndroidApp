package com.couponssystem.adminappforcs.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.couponssystem.adminappforcs.data.model.AuthBody
import com.couponssystem.adminappforcs.data.repository.MainRepository
import com.couponssystem.adminappforcs.utils.Resourse
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class LoginViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun login(authBody: AuthBody) = liveData(Dispatchers.IO) {
        emit(Resourse.loading(data = null))
        try {
            emit(Resourse.success(data = mainRepository.login(authBody)))
        } catch (exception : Exception) {
            emit(Resourse.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}