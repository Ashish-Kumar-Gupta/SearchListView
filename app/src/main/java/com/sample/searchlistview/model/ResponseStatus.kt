package com.sample.searchlistview.model

import com.sample.searchlistview.utils.RESPONSE

class ResponseStatus<T>(val responseType: RESPONSE, val response: T? = null, val error: Throwable? = null) {
    companion object {
        fun <T> loading() = ResponseStatus<T>(RESPONSE.LOADING)
        fun <T> success (response: T) = ResponseStatus<T>(RESPONSE.SUCCESS, response)
        fun <T> error(throwable: Throwable) = ResponseStatus<T>(RESPONSE.ERROR, null, throwable)
    }
}