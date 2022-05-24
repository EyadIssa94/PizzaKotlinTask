package com.example.android.newproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.android.newproject.model.domain.Order
import com.example.android.newproject.model.domain.PizzaSizePrice
import com.example.android.newproject.utils.ResultState
import com.example.android.newproject.viewmodel.PizzaViewModel

private const val TAG = "ACTIVITY"
class MainActivity : AppCompatActivity() {

    private val pizzaViewModel by lazy {
        ViewModelProvider(this)[PizzaViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pizzaViewModel.pizzas.observe(this) {
            when (it) {
                is ResultState.LOADING -> {}
                is ResultState.SUCCESS -> {
                    calculateTotal(it.orders)
                }
                is ResultState.ERROR -> {}
            }
        }

        pizzaViewModel.getAllPizzas()
    }

    private fun calculateTotal(orders: List<Order>) {
        var total = 0
        for (i in orders) {
            total += when (i.size) {
                PizzaSizePrice.SMALL -> i.price
                PizzaSizePrice.MEDIUM -> i.price
                PizzaSizePrice.LARGE -> i.price
                else -> 0
            }
        }
        Log.d(TAG, "Total is $total€") // 27
    }
}