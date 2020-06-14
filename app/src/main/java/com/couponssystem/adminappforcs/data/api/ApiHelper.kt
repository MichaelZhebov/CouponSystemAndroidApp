package com.couponssystem.adminappforcs.data.api

import com.couponssystem.adminappforcs.data.model.AuthBody
import com.couponssystem.adminappforcs.data.model.User

class ApiHelper (private val apiService: ApiService) {

    suspend fun login(authBody: AuthBody) = apiService.login(authBody)

    suspend fun getCompanies() = apiService.getCompanies()

    suspend fun getCompany(id : Long) = apiService.getCompany(id)

    suspend fun deleteCompany(id : Long) = apiService.deleteCompany(id)

    suspend fun updateCompany(id : Long, user: User) = apiService.updateCompany(id, user)

    suspend fun getCustomers() = apiService.getCustomers()

    suspend fun getCustomer(id : Long) = apiService.getCustomer(id)

    suspend fun deleteCustomer(id : Long) = apiService.deleteCustomer(id)

    suspend fun updateCustomer(id : Long, user: User) = apiService.updateCustomer(id, user)

    suspend fun getCoupons() = apiService.getCoupons()

    suspend fun getCoupon(id: Long) = apiService.getCoupon(id)

}