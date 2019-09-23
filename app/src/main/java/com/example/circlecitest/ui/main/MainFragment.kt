package com.example.circlecitest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.circlecitest.data.GameApp
import com.example.circlecitest.databinding.FragmentMainBinding

class MainFragment : Fragment() {

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
        mainGameAppAdapter = MainGameAppAdapter(context!!, viewModel)
        viewDataBinding.gameItems.also {
            it.setHasFixedSize(true)
            it.layoutManager = GridLayoutManager(context!!, 3)
            it.adapter = mainGameAppAdapter
        }
        mainGameAppAdapter.gameAppList = arrayListOf(
                GameApp(1, "first game"),
                GameApp(2, "second game"),
                GameApp(3, "third game"),
                GameApp(4, "fourth game"),
                GameApp(5, "fifth game"),
                GameApp(6, "6th game"),
                GameApp(99, "last game")
        )
    }

    companion object {
        fun newInstance() = MainFragment()
        private const val TAG = "MainFragment"
    }
}
