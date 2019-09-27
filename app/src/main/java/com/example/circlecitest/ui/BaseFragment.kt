package com.example.circlecitest.ui

import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

/**
 * This class will be able to replace to LifecycleScope.
 * And it should be replaced when LifecycleScope is stable released.
 */
abstract class BaseFragment : Fragment(), CoroutineScope {

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
    }
}
