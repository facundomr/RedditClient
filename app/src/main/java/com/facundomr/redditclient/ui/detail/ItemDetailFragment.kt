package com.facundomr.redditclient.ui.detail

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
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
            author.text = entry.username
            title.text = entry.title
            date.text = entry.created.label
            comments.text = "${entry.comments}"

            Glide.with(this).load(entry.thumbnail).into(thumbnail)
            thumbnail.setOnClickListener {
                val uri: Uri = Uri.parse(entry.thumbnail)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent)
                }
            }

            saveToGallery.isVisible = !(entry.thumbnail.isNullOrEmpty() || entry.thumbnail.equals("default"))
            saveToGallery.setOnClickListener {

                Glide.with(context)
                    .asBitmap()
                    .load(entry.thumbnail)
                    .into(object : SimpleTarget<Bitmap?>() {
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap?>?
                        ) {
                            MediaStore.Images.Media.insertImage(activity?.contentResolver, resource, entry.title , "Reddit photo")
                            Toast.makeText(context, R.string.image_saved_to_gallery, Toast.LENGTH_SHORT).show()
                        }
                    })
            }
        }

        return rootView
    }

    companion object {
        const val ARG_ITEM = "item"
    }
}
