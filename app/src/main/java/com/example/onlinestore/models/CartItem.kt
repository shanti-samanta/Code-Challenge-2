package com.example.onlinestore.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItem(
    val category: String,
    val description: String,

    @PrimaryKey
    val id: Int,

    val image: String,
    val price: Double,
    val title: String,
    val quantity: Int = 1
)
