package com.example.toutiaoapplication.ui.comment

import android.telecom.Call
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.repo.ApiServers
import com.example.toutiaoapplication.repo.entities.Comments
import com.example.toutiaoapplication.repo.entities.ResponseUser
import com.example.toutiaoapplication.utils.toast
import com.example.toutiaoapplication.utils.transUnixTime
import kotlinx.android.synthetic.main.item_comment.view.*
import retrofit2.Callback
import retrofit2.Response

class CommentAdapter(var data: List<Comments>) :
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>(){

    class CommentViewHolder(v: View) : RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comment, parent, false)

        // val holder = CommentViewHolder(v)
        // parent.context

        return CommentViewHolder(v)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        // 还要通过data[position].uid拿 username

        // holder.itemView.tv_name.text = usernameList[position]
        holder.itemView.tv_content.text = data[position].ccont
        holder.itemView.tv_time.text = transUnixTime(data[position].ctime)
    }

    override fun getItemCount(): Int = data.size

    companion object {
        const val TAG = "CommentAdapter"
    }
}