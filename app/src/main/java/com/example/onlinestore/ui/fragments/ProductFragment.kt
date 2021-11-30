package com.example.onlinestore.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.onlinestore.R
import com.example.onlinestore.api.RetrofitService
import com.example.onlinestore.db.CartDatabase
import com.example.onlinestore.models.CartItem
import com.example.onlinestore.repository.MainRepository
import com.example.onlinestore.ui.MainViewModel
import com.example.onlinestore.ui.MyViewModelFactory

class ProductFragment : Fragment() {

    lateinit var viewModel : MainViewModel
    private val args by navArgs<ProductFragmentArgs>()
    private val retrofitService= RetrofitService.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=  inflater.inflate(R.layout.fragment_product, container, false)
        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository((retrofitService), CartDatabase(requireContext())))).get(MainViewModel::class.java)

        // Display on Fragment
        Glide.with(view).load(args.productItem.image).into(view.findViewById<ImageView>(R.id.imgProductImage))
        view.findViewById<TextView>(R.id.txvProductTitle).text = args.productItem.title
        view.findViewById<TextView>(R.id.txvProductRating).text = "(" + args.productItem.rating.rate.toString() +")"
        view.findViewById<TextView>(R.id.txvProductPrice).text = "USD " + args.productItem.price.toString()
        view.findViewById<TextView>(R.id.txvProductDescription).text = args.productItem.description
        view.findViewById<RatingBar>(R.id.ratingBar).rating = args.productItem.rating.rate.toFloat()

        //Handling Add to Cart button
        view.findViewById<Button>(R.id.btnAddToCart).setOnClickListener{
            val itemTobeAdded =CartItem(args.productItem.category, args.productItem.description, args.productItem.id, args.productItem.image, args.productItem.price, args.productItem.title)
            viewModel.insert(itemTobeAdded)
            Toast.makeText(context, "SUCCESS: Added to Cart", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}

