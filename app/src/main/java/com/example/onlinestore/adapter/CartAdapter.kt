package com.example.onlinestore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinestore.databinding.CartItemBinding
import com.example.onlinestore.databinding.ProductItemBinding
import com.example.onlinestore.models.CartItem
import com.example.onlinestore.ui.MainViewModel

class CartAdapter(var itemList: List<CartItem>, val viewModel: MainViewModel) : RecyclerView.Adapter<CartAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CartItemBinding.inflate(inflater, parent, false)
        return CartAdapter.MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]
        val category= currentItem.category
        val description= currentItem.description
        val id= currentItem.id
        val image= currentItem.image
        val price= currentItem.price
        val title= currentItem.title

        Glide.with(holder.binding.root).load(image).into(holder.binding.imgCartItem)
        if(title.length > 25)
            holder.binding.txvCartItemTitle.text= title.slice(0..25) + "..."
        else
            holder.binding.txvCartItemTitle.text= title
        holder.binding.txvCartItemPrice.text= "USD " + price.toString()
        holder.binding.txvCartItemQuantity.text = currentItem.quantity.toString()

        holder.binding.btnDeleteItem.setOnClickListener{
            viewModel.delete(currentItem)
        }

        holder.binding.btnAddItem.setOnClickListener{
            val quantity= currentItem.quantity + 1
            if(quantity == 0)
                viewModel.delete(currentItem)
            else{
                val updatedItem= CartItem(category, description, id, image, price, title, quantity)
                viewModel.update(updatedItem)
            }
        }

        holder.binding.btnSubtractItem.setOnClickListener{
            val quantity= currentItem.quantity - 1
            if(quantity == 0)
                viewModel.delete(currentItem)
            else{
                val updatedItem= CartItem(category, description, id, image, price, title, quantity)
                viewModel.update(updatedItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}