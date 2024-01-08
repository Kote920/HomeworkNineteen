package com.example.homeworknineteen.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homeworknineteen.R
import com.example.homeworknineteen.databinding.ItemRecyclerviewBinding
import com.example.homeworknineteen.presentation.model.User


class UserListRecyclerAdapter() :
    ListAdapter<User, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
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

    lateinit var itemOnClick: (User) -> Unit
    lateinit var itemOnLongClick: (Int) ->  Unit
    lateinit var items: MutableList<User>
    lateinit var activateButton: (Boolean) -> Unit

    private var selectedUsers = mutableListOf<User>()








    inner class UserViewHolder(private val binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val user = currentList[adapterPosition]
            binding.tvFirstName.text = user.firstName
            binding.tvLastName.text = user.lastName
            binding.tvEmail.text = user.email

            imgManagement(user.avatar, binding.root.context, binding.ivAvatar)
            listeners(user)



        }

        private fun listeners(user: User) {
            binding.root.setOnClickListener {
                itemOnClick.invoke(user)
            }
            binding.root.setOnLongClickListener {


                if(!user.selected){
                    user.selected = true
                    selectedUsers.add(user)
                    binding.ivSelected.visibility = View.VISIBLE
                    binding.root.setBackgroundResource(R.drawable.arc)
                }else{
                    user.selected = false
                    selectedUsers.remove(user)
                    binding.ivSelected.visibility = View.GONE
                    binding.root.setBackgroundResource(R.drawable.arc_grey)
                }

                activateButton.invoke(selectedUsers.isNotEmpty())

                true
            }


        }

    }

    fun imgManagement(imageUrl: String?, context: Context, imageView: AppCompatImageView){
        imageUrl?.let {
            Glide.with(context)
                .load(imageUrl)
                .fitCenter()
                .into(imageView)
        }
    }

    fun removeItems() {

//            currentList.toMutableList().removeAt(position)
//            notifyItemRemoved(position)
            items = (items - selectedUsers).toMutableList()
            submitList(items)
            selectedUsers.clear()
//            selectedUsers.clear()
    }

}