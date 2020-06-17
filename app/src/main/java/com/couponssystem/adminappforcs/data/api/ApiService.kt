package com.couponssystem.adminappforcs.data.api

import com.couponssystem.adminappforcs.data.model.AuthBody
import com.couponssystem.adminappforcs.data.model.Coupon
import com.couponssystem.adminappforcs.data.model.JwtResponse
import com.couponssystem.adminappforcs.data.model.User
import retrofit2.http.*

const val BASIC_URL_COMPANY = "admin/company"
const val BASIC_URL_CUSTOMER = "admin/customer"
const val BASIC_URL_COUPONS = "admin/coupons"
const val BASIC_URL_COUPON_DETAILS = "admin/coupon"
const val BASIC_URL_LOGIN = "login"

interface ApiService {

    @POST(BASIC_URL_LOGIN)
    suspend fun login(@Body authBody: AuthBody) : JwtResponse

    @GET(BASIC_URL_COMPANY)
    suspend fun getCompanies() : List<User>

    @GET("$BASIC_URL_COMPANY/{id}")
    suspend fun getCompany(@Path("id") id : Long) : User

    @POST(BASIC_URL_COMPANY)
    suspend fun addCompany(@Body user: User) : User

    @DELETE("$BASIC_URL_COMPANY/{id}")
    suspend fun deleteCompany(@Path("id") id : Long) : User

    @PUT("$BASIC_URL_COMPANY/{id}")
    suspend fun updateCompany(@Path("id") id : Long, @Body user: User) : User

    @GET(BASIC_URL_CUSTOMER)
    suspend fun getCustomers() : List<User>

    @GET("$BASIC_URL_CUSTOMER/{id}")
    suspend fun getCustomer(@Path("id") id : Long) : User

    @POST(BASIC_URL_CUSTOMER)
    suspend fun addCustomer(@Body user: User) : User

    @DELETE("$BASIC_URL_CUSTOMER/{id}")
    suspend fun deleteCustomer(@Path("id") id : Long) : User

    @PUT("$BASIC_URL_CUSTOMER/{id}")
    suspend fun updateCustomer(@Path("id") id : Long, @Body user: User) : User

    @GET(BASIC_URL_COUPONS)
    suspend fun getCoupons() : List<Coupon>

    @GET("$BASIC_URL_COUPON_DETAILS/{id}")
    suspend fun getCoupon(@Path("id") id : Long) : Coupon


}