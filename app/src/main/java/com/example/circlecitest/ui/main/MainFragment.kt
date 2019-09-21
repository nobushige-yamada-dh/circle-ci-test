package com.example.circlecitest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.circlecitest.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel = (activity as MainActivity).obtainViewModel()
        val binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    companion object {
        fun newInstance() = MainFragment()
        private const val TAG = "MainFragment"
    }
}
