package com.maxi.petzify.ui.home.postsAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.maxi.petzify.databinding.ItemPostBinding
import com.maxi.petzify.domain.model.post.Post

class PostsViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemPostBinding.bind(view)
    fun render(post:Post){
        //binding.ivPostImages
        binding.tvTitle.text = post.title
        binding.tvSubTitle.text = post.createdAt
        binding.tvDescription.text = post.desc
    }
}