package com.example.utils

data class DataStatus<T>(var status: Status, var data: T? = null, var error: Throwable? = null) {

    enum class Status { LOADING, SUCCESS, ERROR }

    companion object {
        fun <T> success(data: T): DataStatus<T> {
            return DataStatus(Status.SUCCESS, data)
        }

        fun <T> loading(data: T? = null): DataStatus<T> {
            return DataStatus(Status.LOADING, data)
        }

        fun <T> error(error: Throwable? = null, data: T? = null): DataStatus<T> {
            return DataStatus(Status.ERROR, data, error)
        }

//        fun <T> empty(): DataStatus<T> {
//            return DataStatus(Status.EMPTY, null, null)
//        }
    }
}