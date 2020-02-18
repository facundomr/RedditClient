package com.facundomr.redditclient.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.facundomr.redditclient.R
import com.facundomr.redditclient.model.EntryData
import kotlinx.android.synthetic.main.item_list_content.view.*

class EntriesAdapter(
    private val values: List<EntryData>,
    private val onClickListener: View.OnClickListener
) : RecyclerView.Adapter<EntriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = values[position]

        with(holder.itemView) {
            author.text = item.username
            title.text = item.title
            date.text = item.created.label
            comments.text = "${item.comments}"

            unreadIndicator.isVisible = !item.read

            Glide.with(this).load(item.thumbnail).into(thumbnail)

            tag = item
            setOnClickListener {
                unreadIndicator.isVisible = false
                onClickListener.onClick(it)
            }
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}