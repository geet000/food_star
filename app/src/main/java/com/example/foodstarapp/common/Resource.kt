package com.example.foodstarapp.common

sealed class Resource<T>(val data:T? = null,val e:Exception? = null) {
    class Success<T>(data: T):Resource<T>(data)
    class Error<T>(e: Exception, data:T? = null):Resource<T>(data,e)
    class Loading<T>(data:T?=null):Resource<T>(data)
}