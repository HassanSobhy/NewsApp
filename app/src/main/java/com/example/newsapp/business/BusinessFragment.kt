package com.example.newsapp.business


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.newsapp.AllViewModel
import com.example.newsapp.AllViewModelFactory
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentBusinessBinding
import com.example.newsapp.network.NewsApiSection

/**
 * A simple [Fragment] subclass.
 */
class BusinessFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentBusinessBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        val viewModelFactory = AllViewModelFactory(NewsApiSection.SECTION_BUSINESS)
        binding.viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(AllViewModel::class.java)
        Toast.makeText(context,"fff",Toast.LENGTH_LONG).show()
        return binding.root
    }


}
