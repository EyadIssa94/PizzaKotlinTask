package com.example.android.newproject.rest

import com.example.android.newproject.model.Orders
import retrofit2.Response
import retrofit2.http.GET

interface ServiceApi {

    @GET(ORDERS_PATH)
    suspend fun getAllOrders(): Response<Orders>

    companion object {
//      https://raw.githubusercontent.com/pgiani/KotlinTask/main/order.json
        const val BASE_URL = "https://raw.githubusercontent.com/pgiani/KotlinTask/main/"
        private const val ORDERS_PATH = "order.json"


    }
}