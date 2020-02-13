package com.facundomr.redditclient.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.facundomr.redditclient.R
import com.facundomr.redditclient.model.EntryData
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*

class ItemDetailFragment : Fragment() {

    private lateinit var entry: EntryData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM)) {
                entry = it.getSerializable(ARG_ITEM) as EntryData
                activity?.toolbar?.title = entry.title
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        with(rootView) {
            author.text = entry.author
            title.text = entry.title
            date.text = entry.created.toString()
            comments.text = "${entry.comments}"
            Glide.with(this).load(entry.thumbnail).into(thumbnail)
        }

        return rootView
    }

    companion object {
        const val ARG_ITEM = "item"
    }
}
