package com.example.android.newproject.utils

import com.example.android.newproject.model.domain.Order

sealed class ResultState {
    object LOADING : ResultState()
    data class SUCCESS(val orders: List<Order>) : ResultState()
    data class ERROR(val error: Exception) : ResultState()
}
