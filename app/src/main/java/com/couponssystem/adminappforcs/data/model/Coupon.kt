package com.couponssystem.adminappforcs.data.model

data class Coupon (
    val id: Long,
    val companyId: Int,
    val companyName: String,
    val category: String,
    val title: String,
    val description: String,
    val startDate: String,
    val endDate: String,
    val amount: Int,
    val price: Double,
    val image: String
)
