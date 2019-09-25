package com.example.circlecitest.ui.targetappsettings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.circlecitest.R
import com.example.circlecitest.databinding.ActivityTargetAppSettingsBinding
import com.example.circlecitest.ui.obtainViewModel
import kotlinx.android.synthetic.main.activity_target_app_settings.*

/**
 * WARNING:
 * !!! IMPORTANT !!!
 * This is minimum code for Activity.
 * You MUST NOT write any logic here. The logic MUST be written in ViewModel.
 * You MUST keep it.
 */
class TargetAppSettingsActivity : AppCompatActivity() {

    private lateinit var viewModel: TargetAppSettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = obtainViewModel()
        val binding = DataBindingUtil.setContentView<ActivityTargetAppSettingsBinding>(
                this,
                R.layout.activity_target_app_settings
        )
        binding.viewModel = viewModel
        setSupportActionBar(toolbar)
    }

    fun obtainViewModel(): TargetAppSettingsViewModel =
            obtainViewModel(TargetAppSettingsViewModel::class.java)
}
