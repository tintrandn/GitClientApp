package com.example.git.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.git.R
import com.example.git.databinding.ItemUserBinding
import com.example.git.model.User
import java.util.concurrent.Executors

class UserAdapter(val onUserClickedCallback: OnUserClickedCallback) : ListAdapter<
        User,
        UserAdapter.UserViewHolder>(
    AsyncDifferConfig.Builder(DiffCallback())
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
) {

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            Glide.with(itemView)
                .load(user.avatarUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(binding.userAvatarImg)

            binding.userName.text = user.userName
            binding.userScore.text = itemView.context.getString(R.string.user_score, user.score)
            binding.root.setOnClickListener {
                onUserClickedCallback.onUserClickedCallback(user.userName)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(userList: List<User>?) {
        super.submitList(ArrayList<User>(userList ?: listOf()))
    }

    class DiffCallback : DiffUtil.ItemCallback<User>() {

        override fun areItemsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface OnUserClickedCallback {
        fun onUserClickedCallback(userName: String)
    }
}