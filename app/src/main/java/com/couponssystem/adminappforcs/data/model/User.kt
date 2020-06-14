package com.couponssystem.adminappforcs.data.model

data class User (
    val id : Long = 0,
    var fullName: String = "",
    val email: String = "",
    val password: String = "",
    val coupons: List<Coupon> = emptyList(),
    var role: String = "",
    var active: Boolean = true
)