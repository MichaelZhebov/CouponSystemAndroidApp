package com.couponssystem.adminappforcs.ui.main.viewmodel.company

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.couponssystem.adminappforcs.data.repository.MainRepository
import com.couponssystem.adminappforcs.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class CompanyDetailsViewModel(private val mainRepository: MainRepository, private val id: Long) :
    ViewModel() {

    var user = runBlocking {
        mainRepository.getCompany(id)
    }

    fun deleteCompany() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.deleteCompany(id)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun updateCompany() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.updateCompany(id, user = user)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}