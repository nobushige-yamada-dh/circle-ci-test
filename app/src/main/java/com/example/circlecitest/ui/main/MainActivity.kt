package com.example.circlecitest.ui.main

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.circlecitest.MyApplication
import com.example.circlecitest.R
import com.example.circlecitest.ui.ViewModelFactory
import com.example.circlecitest.databinding.ActivityMainBinding
import com.example.circlecitest.util.obtainViewModel
import com.example.circlecitest.util.replaceFragmentInActivity

import kotlinx.android.synthetic.main.activity_main.*

/**
 * WARNING:
 * !!! IMPORTANT !!!
 * This is minimum code for Activity.
 * You MUST NOT write any logic here. The logic MUST be written in ViewModel.
 * You MUST keep it.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = obtainViewModel()
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewModel = viewModel
        setSupportActionBar(toolbar)
        setupViewFragment()

        viewModel.snackBarMessage.apply {
            observe(this@MainActivity, Observer {
                if (it != MainViewModel.Message.NO_MESSAGE) {
                    value = MainViewModel.Message.NO_MESSAGE
                    Snackbar.make(fab, it.name, Snackbar.LENGTH_LONG).show()
                }
            })
        }
    }

    private fun setupViewFragment() {
        supportFragmentManager.findFragmentById(R.id.contentFrame)
            ?: replaceFragmentInActivity(MainFragment.newInstance(), R.id.contentFrame)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun obtainViewModel(): MainViewModel = obtainViewModel(MainViewModel::class.java)
}
