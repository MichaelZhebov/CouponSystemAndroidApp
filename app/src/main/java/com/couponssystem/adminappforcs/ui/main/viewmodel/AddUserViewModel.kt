package com.couponssystem.adminappforcs.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import com.couponssystem.adminappforcs.data.model.User
import com.couponssystem.adminappforcs.data.repository.MainRepository

class AddUserViewModel(private val mainRepository: MainRepository) : ViewModel() {

    var user = User()
}