package com.couponssystem.adminappforcs.data.repository

import com.couponssystem.adminappforcs.data.api.ApiHelper
import com.couponssystem.adminappforcs.data.model.AuthBody
import com.couponssystem.adminappforcs.data.model.User

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun login(authBody: AuthBody) = apiHelper.login(authBody)

    suspend fun getCompanies() = apiHelper.getCompanies()

    suspend fun getCompany(id : Long) = apiHelper.getCompany(id)

    suspend fun deleteCompany(id : Long) = apiHelper.deleteCompany(id)

    suspend fun updateCompany(id : Long, user: User) = apiHelper.updateCompany(id, user)

    suspend fun getCustomers() = apiHelper.getCustomers()

    suspend fun getCustomer(id : Long) = apiHelper.getCustomer(id)

    suspend fun deleteCustomer(id : Long) = apiHelper.deleteCustomer(id)

    suspend fun updateCustomer(id : Long, user: User) = apiHelper.updateCustomer(id, user)

    suspend fun getCoupons() = apiHelper.getCoupons()

    suspend fun getCoupon(id: Long) = apiHelper.getCoupon(id)

    suspend fun addUser(user: User) = apiHelper.addUser(user)
}