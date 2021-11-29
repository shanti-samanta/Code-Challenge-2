package com.example.onlinestore.repository

import androidx.lifecycle.LiveData
import com.example.onlinestore.api.RetrofitService
import com.example.onlinestore.db.CartDao
import com.example.onlinestore.db.CartDatabase
import com.example.onlinestore.models.CartItem

class MainRepository constructor(private val retrofitService: RetrofitService, private val db: CartDatabase) {

    fun getAllProducts() = retrofitService.getAllProducts()

    suspend fun insert(item: CartItem) = db.cartDao().insert(item)
    suspend fun delete(item: CartItem) = db.cartDao().delete(item)
    suspend fun update(item: CartItem) = db.cartDao().update(item)
    fun readAllData() = db.cartDao().readAllData()
}