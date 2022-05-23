package com.example.android.newproject.model.domain

import com.example.android.newproject.model.OrderNetwork

data class Order(
    val sauce: List<String>,
    val size: PizzaSizePrice,
    val toppings: List<String>,
    val type: String,
    var price: String = ""
)

fun List<OrderNetwork>?.mapToDomainData(): List<Order> {
    return mutableListOf<Order>().apply {
        this@mapToDomainData?.map {
            add(
                Order(
                    sauce = it.sauce ?: emptyList(),
                    size = when (it.size) {
                        "small" -> PizzaSizePrice.SMALL
                        "medium" -> PizzaSizePrice.MEDIUM
                        "large" -> PizzaSizePrice.LARGE
                        else -> PizzaSizePrice.NO_SIZE
                    },
                    toppings = it.toppings ?: emptyList(),
                    type = it.type ?: "No type"
                ).apply {
                    price = when (size) {
                        PizzaSizePrice.SMALL -> size.price
                        PizzaSizePrice.MEDIUM -> size.price
                        PizzaSizePrice.LARGE -> size.price
                        else -> ""
                    }
                }
            )
        }
    }
}
