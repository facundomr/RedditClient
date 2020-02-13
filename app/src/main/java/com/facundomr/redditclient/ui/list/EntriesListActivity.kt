package com.facundomr.redditclient.ui.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.facundomr.redditclient.ui.detail.ItemDetailActivity
import com.facundomr.redditclient.ui.detail.ItemDetailFragment
import com.facundomr.redditclient.R

import com.facundomr.redditclient.model.EntryData
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*

class EntriesListActivity : AppCompatActivity() {

    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        val viewModel = ViewModelProvider(this).get(EntriesListViewModel::class.java)

        viewModel.data.observe(this, Observer<List<EntryData>> { data ->
            setupRecyclerView(item_list, data)
        })

        setSupportActionBar(toolbar)
        toolbar.title = title

        if (item_detail_container != null) {
            twoPane = true
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, data: List<EntryData>) {

        val onEntryClickListener = View.OnClickListener { v ->
            val item = v.tag as EntryData
            if (twoPane) {
                val fragment = ItemDetailFragment()
                    .apply {
                        arguments = Bundle().apply {
                            putSerializable(ItemDetailFragment.ARG_ITEM, item)
                        }
                    }
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit()
            } else {
                val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                    putExtra(ItemDetailFragment.ARG_ITEM, item)
                }
                v.context.startActivity(intent)
            }
        }

        recyclerView.adapter = EntriesAdapter(data, onEntryClickListener)
    }
}
