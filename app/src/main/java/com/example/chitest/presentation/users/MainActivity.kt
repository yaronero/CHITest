package com.example.chitest.presentation.users

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.chitest.R
import com.example.chitest.databinding.ActivityMainBinding
import com.example.chitest.domain.model.User
import com.example.chitest.presentation.adduser.AddUserFragment
import com.example.chitest.presentation.userinfo.UserInfoFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private val userAdapter by lazy {
        UserAdapter(
            onItemClickListener = ::onItemClickListener,
            onCheckBoxClickListener = ::onCheckBoxClickListener
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUser.adapter = userAdapter

        binding.toolbar.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener when (it.itemId) {
                R.id.item_add_new_user -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, AddUserFragment.newInstance())
                        .addToBackStack(null)
                        .commit()
                    true
                }

                else -> false
            }
        }

        setupObservers()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.users.collect {
                    userAdapter.submitList(it)
                }
            }
        }
    }

    private fun onItemClickListener(user: User) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, UserInfoFragment.newInstance(user.id))
            .addToBackStack(null)
            .commit()
    }

    private fun onCheckBoxClickListener(user: User) {
        viewModel.updateUser(user)
    }
}