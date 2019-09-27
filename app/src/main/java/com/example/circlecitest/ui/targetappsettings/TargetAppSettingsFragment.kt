package com.example.circlecitest.ui.targetappsettings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.circlecitest.databinding.FragmentTargetAppSettingsBinding

class TargetAppSettingsFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentTargetAppSettingsBinding
    private lateinit var viewModel: TargetAppSettingsViewModel
    private lateinit var mainGameAppAdapter: TargetAppSettingsAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel = (activity as TargetAppSettingsActivity).obtainViewModel()
        viewDataBinding = FragmentTargetAppSettingsBinding.inflate(inflater, container, false)
        viewDataBinding.viewModel = viewModel
        viewModel.onStart()
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        mainGameAppAdapter = TargetAppSettingsAdapter(this, viewModel)
        viewDataBinding.targetAppItems.also {
            it.setHasFixedSize(true)
            it.adapter = mainGameAppAdapter
        }
    }

    companion object {
        fun newInstance() = TargetAppSettingsFragment()
        private const val TAG = "TargetAppSettingsFragment"
    }
}
