package com.couponssystem.adminappforcs.utils

import com.couponssystem.adminappforcs.utils.Status.SUCCESS
import com.couponssystem.adminappforcs.utils.Status.ERROR
import com.couponssystem.adminappforcs.utils.Status.LOADING


data class Resourse<out T>(val status : Status, val data : T?, val message : String?) {

    companion object {
        fun <T> success(data: T): Resourse<T> = Resourse(status = SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String?) : Resourse<T> = Resourse(status = ERROR, data = data, message = message)

        fun<T> loading(data: T?) : Resourse<T> = Resourse(status = LOADING, data = data, message = null)
    }
}