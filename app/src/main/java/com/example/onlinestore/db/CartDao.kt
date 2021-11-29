package com.example.onlinestore.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.onlinestore.models.CartItem

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item : CartItem)

    @Delete
    fun delete(item: CartItem)

    @Query("SELECT * FROM cart_items")
    fun readAllData(): LiveData<List<CartItem>>

    @Update
    fun update(item : CartItem)
}