package com.example.chitest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.chitest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CounterContract {

    private lateinit var binding: ActivityMainBinding

    private var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        savedInstanceState?.let { bundle ->
            counter = bundle.getInt(COUNTER_KEY)
        }

        binding.tvCounter.text = counter.toString()

        binding.btnOpenFragment.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, CounterFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(COUNTER_KEY, counter)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                supportFragmentManager.popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun getCounterValue(): Int = counter

    override fun incrementCounter() {
        counter++
        binding.tvCounter.text = counter.toString()
    }

    companion object {
        private const val COUNTER_KEY = "counter"
    }
}