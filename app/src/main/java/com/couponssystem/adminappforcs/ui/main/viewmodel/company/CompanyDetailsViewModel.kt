package com.couponssystem.adminappforcs.ui.main.viewmodel.company

import androidx.lifecycle.ViewModel
import com.couponssystem.adminappforcs.data.repository.MainRepository
import kotlinx.coroutines.runBlocking

class CompanyDetailsViewModel(private val mainRepository: MainRepository, private val id : Long) : ViewModel() {

    var user = runBlocking {
        mainRepository.getCompany(id)
    }

    fun deleteCompany() {
        runBlocking {
            mainRepository.deleteCompany(id)
        }
    }

    fun updateCompany() {
        runBlocking {
            mainRepository.updateCompany(id, user)
        }
    }
}