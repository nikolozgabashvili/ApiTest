package com.example.apitest.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apitest.MyList
import com.example.apitest.adapter.RecyclerAdapter
import com.example.apitest.classes.CardClass
import com.example.apitest.databinding.FragmentFirstBinding
import com.example.apitest.retrofit.ApiInterface
import com.example.apitest.retrofit.RetrofitClass
import kotlinx.coroutines.launch
import java.lang.Exception


class FirstFragment : Fragment(),RecyclerAdapter.OnItemClickListener {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var recycler:RecyclerView
    private lateinit var adapter :RecyclerAdapter
    private lateinit var data:List<CardClass>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root
    }

    override  fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        if (MyList.lst == null){
            getResponse()
        }
        else{
            updateUI(MyList.lst)
        }



    }

    private fun init() {
        recycler = binding.recycler
    }


    private fun getResponse() {
        viewLifecycleOwner.lifecycleScope.launch {
            val response = RetrofitClass.api.getColors()

            Log.d("response","${response.body()}")

            response.body()
            data = response.body()!!
            updateUI(data)

        }
    }

    private fun updateUI(data: List<CardClass>?){
        adapter = RecyclerAdapter(data!!,this)
        recycler.layoutManager = LinearLayoutManager(this.requireContext())
        recycler.adapter  = adapter
        MyList.lst = data


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int) {
        val clickedItem :CardClass   = data[position]
        val amount: Float = 1.0f
        val action = FirstFragmentDirections.actionFirstFragmentToInfoFragment(clickedItem)
        findNavController().navigate(action)

    }

}
