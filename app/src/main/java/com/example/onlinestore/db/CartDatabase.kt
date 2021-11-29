package com.example.onlinestore.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.onlinestore.models.CartItem

@Database(entities = [CartItem::class], version = 1, exportSchema = false)
abstract class CartDatabase  : RoomDatabase(){

    abstract fun cartDao() : CartDao

    companion object
    {
        @Volatile
        private var INSTANCE: CartDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: createDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CartDatabase::class.java,
                "CartDatabase.db"
            ).build()
    }
}