package com.example.homeworknineteen.presentation.userList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworknineteen.data.model.UserResponseDto
import com.example.homeworknineteen.databinding.ItemRecyclerviewBinding


class UserListRecyclerAdapter() :
    ListAdapter<UserResponseDto, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<UserResponseDto>() {
        override fun areItemsTheSame(oldItem: UserResponseDto, newItem: UserResponseDto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserResponseDto, newItem: UserResponseDto): Boolean {
            return oldItem == newItem
        }

    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

            return UserViewHolder(
                ItemRecyclerviewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UserViewHolder) {
            holder.bind()

        }

    }

    lateinit var itemOnClick: (UserResponseDto) -> Unit






    inner class UserViewHolder(private val binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val user = currentList[adapterPosition]
            binding.tvFirstName.text = user.firstName
            binding.tvLastName.text = user.lastName
            binding.tvFirstName.text = user.firstName
            listeners(user)

        }

        private fun listeners(user: UserResponseDto) {
            binding.root.setOnClickListener {
                itemOnClick.invoke(user)
            }

        }

    }

}