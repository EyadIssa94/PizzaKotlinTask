package com.example.android.newproject.model


import com.google.gson.annotations.SerializedName

data class OrderNetwork(
    @SerializedName("sauce")
    val sauce: List<String>?,
    @SerializedName("size")
    val size: String?,
    @SerializedName("toppings")
    val toppings: List<String>?,
    @SerializedName("type")
    val type: String?
)