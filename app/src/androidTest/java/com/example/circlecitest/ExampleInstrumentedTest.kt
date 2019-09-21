package com.example.circlecitest

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.circlecitest.data.source.AppRepositoryImpl
import com.example.circlecitest.data.source.local.LocalDataSource
import com.example.circlecitest.di.AppModule
import com.example.circlecitest.di.DaggerAppComponent
import com.example.circlecitest.ui.main.MainActivity
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var activityTestRule: ActivityTestRule<MainActivity> =
            ActivityTestRule(MainActivity::class.java, false, false)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val app = appContext.applicationContext as MyApplication
        val localDataSource = mock(LocalDataSource::class.java)
        val appComponent = DaggerAppComponent.builder().application(app)
                .appModule(object : AppModule() {
                    override fun provideRepository(app: MyApplication) =
                            AppRepositoryImpl.getInstance(localDataSource)
                })
                .build()
        app.appComponent = appComponent
        activityTestRule.launchActivity(Intent(appContext, MainActivity::class.java))
        assertEquals("com.example.circlecitest", appContext.packageName)
    }
}
