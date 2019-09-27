package com.example.circlecitest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.circlecitest.databinding.FragmentMainBinding
import com.example.circlecitest.ui.BaseFragment

class MainFragment : BaseFragment() {

    private lateinit var viewDataBinding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var mainGameAppAdapter: MainGameAppAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel = (activity as MainActivity).obtainViewModel()
        viewDataBinding = FragmentMainBinding.inflate(inflater, container, false)
        viewDataBinding.viewModel = viewModel
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        mainGameAppAdapter = MainGameAppAdapter(this, viewModel)
        viewDataBinding.gameItems.also {
            it.setHasFixedSize(true)
            it.adapter = mainGameAppAdapter
        }
    }

    companion object {
        fun newInstance() = MainFragment()
        private const val TAG = "MainFragment"
    }
}
