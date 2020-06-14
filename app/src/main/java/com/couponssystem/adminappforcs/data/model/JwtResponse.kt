package com.couponssystem.adminappforcs.data.model

data class JwtResponse(
    val email: String,
    val role: String,
    val id: Int,
    val fullName: String,
    val accessToken: String,
    val tokenType: String
)
