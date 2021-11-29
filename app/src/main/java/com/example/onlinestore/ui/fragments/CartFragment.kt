package com.example.onlinestore.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlinestore.R
import com.example.onlinestore.adapter.CartAdapter
import com.example.onlinestore.api.RetrofitService
import com.example.onlinestore.databinding.FragmentCartBinding
import com.example.onlinestore.db.CartDatabase
import com.example.onlinestore.repository.MainRepository
import com.example.onlinestore.ui.MainViewModel
import com.example.onlinestore.ui.MyViewModelFactory

class CartFragment : Fragment() {

    lateinit var viewModel : MainViewModel
    private var _binding: FragmentCartBinding? = null
    private val  binding get() = _binding!!

    private val TAG = "ListFragment"
    private val retrofitService= RetrofitService.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCartBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository((retrofitService), CartDatabase(requireContext())))).get(MainViewModel::class.java)
        val adapter = CartAdapter(listOf(), viewModel)
        //setup Recycler View
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.readAllData().observe(viewLifecycleOwner, Observer {
            adapter.itemList = it
            adapter.notifyDataSetChanged()
        })
        return binding.root
    }
}