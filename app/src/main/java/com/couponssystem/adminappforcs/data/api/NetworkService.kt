package com.couponssystem.adminappforcs.data.api

import com.couponssystem.adminappforcs.utils.LoggedInUser
import okhttp3.Interceptor
import okhttp3.Interceptor.Companion.invoke
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkService {

    private const val BASE_URL = "https://couponssystem.herokuapp.com/"

    private val loggingInterceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val basicInterceprot: Interceptor = invoke { chain ->
        val newUrl = chain
            .request()
            .url
            .newBuilder()
            .build()

        val requst = chain
            .request()
            .newBuilder()
            .header("Authorization", "Bearer " + LoggedInUser.token)
            .url((newUrl))
            .build()

        return@invoke chain.proceed(requst)
    }

    private val client : OkHttpClient = OkHttpClient
        .Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60,TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(basicInterceprot)
        .build()

    fun retrofitService() : ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }

}