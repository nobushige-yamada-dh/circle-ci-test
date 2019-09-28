package com.example.circlecitest.ui.targetappsettings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.circlecitest.data.GameApp
import com.example.circlecitest.data.source.AppRepository
import kotlinx.coroutines.launch
import java.util.ArrayList

/**
 * WARNING:
 * !!! IMPORTANT !!!
 * This is an implementation of all logic of TargetAppSettingsActivity and TargetAppSettingsFragment.
 * You MUST NOT write code depends on Android, except LiveData and ViewModel.
 * This class (all logic about view) can test by Unit Test with mock easily, without Android device or emulator.
 * You MUST keep it.
 */
class TargetAppSettingsViewModel(
        private val appRepository: AppRepository
) : ViewModel() {

    inner class Item(val gameApp: GameApp, initChecked: Boolean) {
        val isChecked = MutableLiveData(initChecked).also {
            it.observeForever() {
                it?.also {
                    viewModelScope.launch {
                        if (it) {
                            val id = appRepository.insertGameAppIfNotExists(gameApp)
                            if (id > 0) {
                                gameApp.id = id
                            }
                        } else {
                            if (appRepository.deleteGameApp(gameApp) > 0) {
                                gameApp.id = 0
                            }
                        }
                    }
                }
            }
        }
    }

    private val _items = MutableLiveData<List<Item>>(ArrayList(0))
    val items: LiveData<List<Item>>
        get() = _items

    fun onStart() {
        viewModelScope.launch {
            _items.postValue(appRepository.getInstalledApplications()
                    .map {
                        Item(
                                it,
                                appRepository.getGameAppsByApplicationId(it.applicationId).isNotEmpty()
                        )
                    }
                    .toList())
        }
    }
}
