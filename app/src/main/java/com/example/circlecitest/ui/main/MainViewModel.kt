package com.example.circlecitest.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.circlecitest.data.source.AppRepository

/**
 * WARNING:
 * !!! IMPORTANT !!!
 * This is an implementation of all logic of MainActivity and MainFragment.
 * You MUST NOT write code depends on Android, except LiveData and ViewModel.
 * This class (all logic about view) can test by Unit Test with mock easily, without Android device or emulator.
 * You MUST keep it.
 */
class MainViewModel(
        private val appRepository: AppRepository
) : ViewModel() {

    enum class LaunchScreen {
        REGISTER,
        HELP
    }

    val launchScreen = MutableLiveData<LaunchScreen>()

    enum class Message {
        ERROR
    }

    val snackBarMessage = MutableLiveData<Message>()

    fun onClickAdd() {
        launchScreen.value = LaunchScreen.REGISTER
    }

    fun onClickHelp() {
        launchScreen.value = LaunchScreen.HELP
    }

    fun onClickTargetAppSettings() {
        launchScreen.value = LaunchScreen.REGISTER
    }
}
