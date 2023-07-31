package com.example.chitest.presentation.adduser

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.chitest.R
import com.example.chitest.databinding.FragmentAddUserBinding
import com.example.chitest.utils.toddMMyyyyFormat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Calendar

@AndroidEntryPoint
class AddUserFragment : Fragment() {

    private var _binding: FragmentAddUserBinding? = null
    private val binding: FragmentAddUserBinding
        get() = _binding!!

    private val viewModel: AddUserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etName.doOnTextChanged { text, _, _, _ ->
            viewModel.updateName(text.toString())
        }

        binding.etAge.doOnTextChanged { text, _, _, _ ->
            viewModel.updateAge(text.toString().toInt())
        }

        binding.cbIsStudent.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateIsStudent(isChecked)
        }

        binding.btnSetDate.setOnClickListener {
            showDatePickerDialog()
        }

        binding.btnAddNewUser.setOnClickListener {
            viewModel.createUser()
            parentFragmentManager.popBackStack()
            Toast.makeText(
                requireContext(),
                requireActivity().getString(R.string.user_added_successfully),
                Toast.LENGTH_LONG
            ).show()
        }

        setupObservers()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.user.collect {
                    binding.tvDateOfBirth.text = it.birthOfDate.toddMMyyyyFormat()
                }
            }
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val dateSetListener =
            OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                viewModel.updateBirthOfDate(calendar.time.time)
            }
        DatePickerDialog(
            requireContext(),
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance(): AddUserFragment {
            return AddUserFragment()
        }
    }
}