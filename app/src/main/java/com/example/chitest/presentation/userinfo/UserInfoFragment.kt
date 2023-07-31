package com.example.chitest.presentation.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.chitest.R
import com.example.chitest.databinding.FragmentUserInfoBinding
import com.example.chitest.utils.toddMMyyyyFormat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserInfoFragment : Fragment() {

    private var _binding: FragmentUserInfoBinding? = null
    private val binding: FragmentUserInfoBinding
        get() = _binding!!

    private val viewModel: UserInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = requireArguments()
        if (args.containsKey(USER_ID)) {
            val userId = requireArguments().getInt(USER_ID)
            viewModel.getUserById(userId)
        }

        setupObservers()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.user.collect { user ->
                    binding.tvName.text = user.name
                    binding.tvAge.text = user.age.toString()
                    binding.tvDateOfBirth.text = user.birthOfDate.toddMMyyyyFormat()
                    binding.tvIsStudent.text = if (user.isStudent) {
                        requireContext().getString(R.string.yes)
                    } else {
                        requireContext().getString(R.string.no)

                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val USER_ID = "userId"

        fun newInstance(userId: Int): UserInfoFragment {
            return UserInfoFragment().apply {
                arguments = Bundle().apply {
                    putInt(USER_ID, userId)
                }
            }
        }
    }
}