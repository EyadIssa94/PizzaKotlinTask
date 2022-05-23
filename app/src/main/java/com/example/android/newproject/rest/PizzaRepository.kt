package com.example.android.newproject.rest

import com.example.android.newproject.model.OrderNetwork
import com.example.android.newproject.model.domain.Order
import com.example.android.newproject.model.domain.PizzaSizePrice
import com.example.android.newproject.model.domain.mapToDomainData
import com.example.android.newproject.utils.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface PizzaRepository {
    fun getAllOrders(): Flow<ResultState>
}

class PizzaRepositoryImpl(
    private val apiServiceApi: ServiceApi = RestInjection.apiService
) : PizzaRepository {
    override fun getAllOrders() = flow {
        emit(ResultState.LOADING)

        try {
            val response = apiServiceApi.getAllOrders()
            if (response.isSuccessful) {
                response.body()?.let { orders ->
                    emit(ResultState.SUCCESS(orders.orderNetwork.mapToDomainData()))
                } ?: throw Exception("DATA IS NULL")
            } else {
                throw Exception(response.errorBody()?.string() ?: "ERROR")
            }
        } catch (e: Exception) {
            emit(ResultState.ERROR(e))
        }
    }
}