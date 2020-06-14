package com.couponssystem.adminappforcs.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.couponssystem.adminappforcs.data.api.ApiHelper
import com.couponssystem.adminappforcs.data.repository.MainRepository
import com.couponssystem.adminappforcs.ui.main.viewmodel.*
import com.couponssystem.adminappforcs.ui.main.viewmodel.company.CompanyDetailsViewModel
import com.couponssystem.adminappforcs.ui.main.viewmodel.company.CompanyListViewModel
import com.couponssystem.adminappforcs.ui.main.viewmodel.coupon.CouponDetailsViewModel
import com.couponssystem.adminappforcs.ui.main.viewmodel.coupon.CouponListViewModel
import com.couponssystem.adminappforcs.ui.main.viewmodel.customer.CustomerDetailsViewModel
import com.couponssystem.adminappforcs.ui.main.viewmodel.customer.CustomerListViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiHelper: ApiHelper, private val id: Long) :
    ViewModelProvider.Factory {

    constructor(apiHelper: ApiHelper) : this(apiHelper, 0)

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(MainRepository(apiHelper)) as T
        }
        if (modelClass.isAssignableFrom(CompanyListViewModel::class.java)) {
            return CompanyListViewModel(MainRepository(apiHelper)) as T
        }
        if (modelClass.isAssignableFrom(CustomerListViewModel::class.java)) {
            return CustomerListViewModel(MainRepository(apiHelper)) as T
        }
        if (modelClass.isAssignableFrom(CompanyDetailsViewModel::class.java)) {
            return CompanyDetailsViewModel(MainRepository(apiHelper), id) as T
        }
        if (modelClass.isAssignableFrom(CustomerDetailsViewModel::class.java)) {
            return CustomerDetailsViewModel(MainRepository(apiHelper), id) as T
        }
        if (modelClass.isAssignableFrom(CouponListViewModel::class.java)) {
            return CouponListViewModel(MainRepository(apiHelper)) as T
        }
        if (modelClass.isAssignableFrom(CouponDetailsViewModel::class.java)) {
            return CouponDetailsViewModel(MainRepository(apiHelper), id) as T
        }
        if (modelClass.isAssignableFrom(AddUserViewModel::class.java)) {
            return AddUserViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}