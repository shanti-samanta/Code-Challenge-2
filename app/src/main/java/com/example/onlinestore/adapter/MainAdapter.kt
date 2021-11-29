package com.example.onlinestore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinestore.databinding.ProductItemBinding
import com.example.onlinestore.models.Product
import com.example.onlinestore.ui.fragments.ListFragmentDirections
import com.example.onlinestore.ui.fragments.ProductFragmentDirections

class MainAdapter : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    var products = mutableListOf<Product>()

    class MyViewHolder (val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val productItem = products[position]
        holder.binding.txvTitle.text= productItem.title
        holder.binding.txvPrice.text= "USD " + productItem.price.toString()
        holder.binding.txvCategory.text = productItem.category
        Glide.with(holder.binding.root).load(productItem.image).into(holder.binding.imgProduct)

        holder.binding.productCard.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToProductFragment(productItem)
            val navController= Navigation.findNavController(holder.itemView)
            navController!!.navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun setProductList(products : List<Product>){
        this.products = products.toMutableList()
        notifyDataSetChanged()
    }
}