package com.couponssystem.adminappforcs.ui.main.viewmodel.company

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.couponssystem.adminappforcs.data.repository.MainRepository
import com.couponssystem.adminappforcs.utils.Resourse
import kotlinx.coroutines.Dispatchers

class CompanyListViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getCompanies() = liveData(Dispatchers.IO) {
        emit(Resourse.loading(data = null))
        try {
            emit(Resourse.success(data = mainRepository.getCompanies()))
        } catch (exception : Exception) {
            emit(Resourse.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}