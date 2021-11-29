package com.example.onlinestore.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlinestore.R
import com.example.onlinestore.adapter.MainAdapter
import com.example.onlinestore.api.RetrofitService
import com.example.onlinestore.databinding.FragmentListBinding
import com.example.onlinestore.db.CartDatabase
import com.example.onlinestore.repository.MainRepository
import com.example.onlinestore.ui.MainViewModel
import com.example.onlinestore.ui.MyViewModelFactory

class ListFragment : Fragment() {


    lateinit var viewModel : MainViewModel
    private var _binding: FragmentListBinding? = null
    private val  binding get() = _binding!!

    private val TAG = "ListFragment"
    private val retrofitService= RetrofitService.getInstance()
    val adapter = MainAdapter()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)

        //set up ViewModel
        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository((retrofitService), CartDatabase(requireContext())))).get(MainViewModel::class.java)

        //setup Recycler View
        binding.rvProductList.adapter = adapter
        binding.rvProductList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.itemList.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onCreateView : $it")
            adapter.setProductList(it)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {

        })

        viewModel.getAllProducts()

        return binding.root
    }
}