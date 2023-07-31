package com.example.chitest.presentation.users

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chitest.R
import com.example.chitest.databinding.UserItemBinding
import com.example.chitest.domain.model.User
import com.example.chitest.utils.toddMMyyyyFormat

class UserAdapter(
    private val onItemClickListener: (User) -> Unit,
    private val onCheckBoxClickListener: (User) -> Unit
) : ListAdapter<User, UserViewHolder>(UserDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            parent.context,
            onItemClickListener,
            onCheckBoxClickListener
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

class UserViewHolder(
    private val binding: UserItemBinding,
    private val context: Context,
    private val onItemClickListener: (User) -> Unit,
    private val onCheckBoxClickListener: (User) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        binding.tvNameAge.text = context.getString(R.string.user_name_age, user.name, user.age)
        binding.tvDateOfBirth.text = user.birthOfDate.toddMMyyyyFormat()
        binding.cbIsStudent.isChecked = user.isStudent

        binding.cbIsStudent.setOnCheckedChangeListener { _, isChecked ->
            onCheckBoxClickListener(user.copy(
                isStudent = isChecked
            ))
        }

        binding.root.setOnClickListener {
            onItemClickListener(user)
        }
    }
}

class UserDiffUtil : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
       return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}