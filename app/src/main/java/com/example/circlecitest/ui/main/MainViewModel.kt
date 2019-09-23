package com.example.circlecitest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.circlecitest.data.GameApp
import com.example.circlecitest.data.source.AppRepository
import java.util.ArrayList

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

    enum class Message {
        NO_MESSAGE,
        FAB_PUSHED
    }

    val snackBarMessage = MutableLiveData<Message>(Message.NO_MESSAGE)

    private val _items = MutableLiveData<List<GameApp>>(ArrayList(0))
    val items: LiveData<List<GameApp>>
        get() = _items

    init {
        _items.value = arrayListOf(
                GameApp(1, "first game"),
                GameApp(2, "second game"),
                GameApp(3, "third game"),
                GameApp(4, "fourth game"),
                GameApp(5, "fifth game"),
                GameApp(6, "6th game"),
                GameApp(99, "last game")
        )
    }

    val clickedGameApp = MutableLiveData<GameApp>()

    fun doSomething(): String = "Did something"

    fun onClickGameApp(gameApp: GameApp) {
        clickedGameApp.value = gameApp
    }

    fun onClickFab() {
        snackBarMessage.value = Message.FAB_PUSHED
    }
}
