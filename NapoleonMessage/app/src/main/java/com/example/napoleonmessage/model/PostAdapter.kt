package com.example.napoleonmessage.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.napoleonmessage.R

class PostAdapter(private val context: Context, private val listOfPosts: List<Post?>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>(){

    private lateinit var onItemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        var post: Post? = listOfPosts.get(position)
        holder.txtTitle?.text = post?.title
        holder.txtMessage?.text = post?.body
    }

    override fun getItemCount(): Int {
        return listOfPosts.size
    }

    fun setOnItemClickListener (onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

    inner class PostViewHolder(view: View): RecyclerView.ViewHolder(view){

        var txtTitle: TextView? = null
        var txtMessage: TextView? = null

        init{
            txtTitle = view.findViewById(R.id.title)
            txtMessage = view.findViewById(R.id.message)
            view.setOnClickListener{
                onItemClickListener.onItemClick(listOfPosts.get(adapterPosition))
            }
        }

    }

   interface OnItemClickListener{
       fun onItemClick(post : Post?)
   }
}