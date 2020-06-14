package com.couponssystem.adminappforcs.ui.main.viewmodel.company

import androidx.lifecycle.ViewModel
import com.couponssystem.adminappforcs.data.repository.MainRepository
import kotlinx.coroutines.runBlocking

class CompanyDetailsViewModel(private val mainRepository: MainRepository, private val id : Long) : ViewModel() {

    var user = runBlocking {
        mainRepository.getCompany(id)
    }

    fun getFullName() : String {
        return user.fullName
    }

    fun setFullName(name : String) {
        user.fullName = name
    }

    fun getActive() : Boolean {
        return user.active
    }

    fun setActive(active : Boolean) {
        user.active = active
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