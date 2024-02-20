package com.maxi.petzify.ui.home.postsAdapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maxi.petzify.R
import com.maxi.petzify.domain.model.post.Post

class PostsAdapter(
    private var postList: List<Post> = emptyList()
) : RecyclerView.Adapter<PostsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PostsViewHolder(layoutInflater.inflate(R.layout.item_post, parent, false))
    }

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val item = postList[position]
        holder.render(item)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listOfPosts:List<Post>){
        this.postList = listOfPosts
        notifyDataSetChanged()
    }
}