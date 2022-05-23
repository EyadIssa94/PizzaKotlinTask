package com.example.android.newproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.newproject.rest.PizzaRepository
import com.example.android.newproject.rest.PizzaRepositoryImpl
import com.example.android.newproject.utils.ResultState
import kotlinx.coroutines.*

private const val TAG = "PizzaViewModel"

class PizzaViewModel(
    private val pizzaRepository: PizzaRepository = PizzaRepositoryImpl(),
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + ioDispatcher)
) : ViewModel() {

    private val _pizzaData: MutableLiveData<ResultState> = MutableLiveData(ResultState.LOADING)
    val pizzas: LiveData<ResultState> get() = _pizzaData

    fun getAllPizzas() {
        coroutineScope.launch {
            pizzaRepository.getAllOrders().collect {
                _pizzaData.postValue(it)
            }
        }
    }
}