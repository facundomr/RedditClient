package com.facundomr.redditclient.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
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
            author.text = item.author
            title.text = item.title
            date.text = item.created.toString()

            isVisible = !item.read

            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}