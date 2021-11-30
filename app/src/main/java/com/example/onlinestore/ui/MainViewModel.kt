package com.example.onlinestore.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinestore.db.CartDatabase
import com.example.onlinestore.models.CartItem
import com.example.onlinestore.models.Product
import com.example.onlinestore.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository)  : ViewModel()
{

    val itemList = MutableLiveData<List<Product>>()
    val errorMessage = MutableLiveData<String>()

    val cartTotal = MutableLiveData<Double>()

    fun getAllProducts() {

        val response = repository.getAllProducts()
        response.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                itemList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun insert(item: CartItem) = GlobalScope.launch {
        repository.insert(item)
    }

    // In coroutines thread delete item in delete function.
    fun delete(item: CartItem) = GlobalScope.launch {
        repository.delete(item)
    }

    fun update(item: CartItem) = GlobalScope.launch {
        repository.update(item)
    }

    //Here we initialized allGroceryItems function with repository
    fun readAllData() = repository.readAllData()

}