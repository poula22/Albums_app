package com.bosta_task.domain.apiManager

interface ApiManager {
    fun <T> buildWebService(webService: Class<T>): T
}