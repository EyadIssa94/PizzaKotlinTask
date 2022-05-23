package com.example.android.newproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.android.newproject.utils.ResultState
import com.example.android.newproject.viewmodel.PizzaViewModel

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
                    Log.d("ACTIVITY", "getAllPizzas: ${it.orders}")
                }
                is ResultState.ERROR -> {}
            }
        }

        pizzaViewModel.getAllPizzas()
    }
}