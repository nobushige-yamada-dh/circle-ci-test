package com.example.circlecitest.ui.targetappsettings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.circlecitest.data.GameApp
import com.example.circlecitest.data.source.AppExecutors
import com.example.circlecitest.data.source.AppRepository
import java.util.ArrayList
import java.util.concurrent.Executor

/**
 * WARNING:
 * !!! IMPORTANT !!!
 * This is an implementation of all logic of TargetAppSettingsActivity and TargetAppSettingsFragment.
 * You MUST NOT write code depends on Android, except LiveData and ViewModel.
 * This class (all logic about view) can test by Unit Test with mock easily, without Android device or emulator.
 * You MUST keep it.
 */
class TargetAppSettingsViewModel(
        private val appRepository: AppRepository,
        private val appExecutors: AppExecutors
) : ViewModel() {

    class Item(val gameApp: GameApp, isChecked: Boolean) {
        val isChecked = MutableLiveData(isChecked)
    }

    private val _items = MutableLiveData<List<Item>>(ArrayList(0))
    val items: LiveData<List<Item>>
        get() = _items

    fun onStart() {
        appExecutors.diskIoExecutor.execute {
            appRepository.getInstalledApplications()
        }
    }
}
