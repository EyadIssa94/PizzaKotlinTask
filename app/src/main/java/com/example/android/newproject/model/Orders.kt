package com.example.android.newproject.model


import com.google.gson.annotations.SerializedName

data class Orders(
    @SerializedName("order")
    val orderNetwork: List<OrderNetwork>?
)