package com.example.radmushroom.bigdig_testtask_A.view


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.radmushroom.bigdig_testtask_A.R
import com.example.radmushroom.bigdig_testtask_A.adapter.LinkListAdapter
import com.example.radmushroom.contentprovider.link.LinkBean
import com.example.radmushroom.contentprovider.link.LinkColumns
import com.example.radmushroom.contentprovider.link.LinkModel
import com.example.radmushroom.contentprovider.link.LinkSelection
import kotlinx.android.synthetic.main.fragment_history.*


class HistoryFragment : Fragment(), LinkListAdapter.OnPositionClickListener, MainActivity.SortSelectedListener {

    private var linkSelection = LinkSelection()
    private var linksListAdapter = LinkListAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyRecyclerView.adapter = linksListAdapter
    }

    override fun onStart() {
        super.onStart()
        val projection = arrayOf(LinkColumns._ID, LinkColumns.LINK, LinkColumns.TIME, LinkColumns.STATUS)
        val linkCursor = linkSelection.query(activity?.applicationContext, projection)
        val links = mutableListOf<LinkModel>()
        while (linkCursor.moveToNext()){
            val link = LinkBean.newInstance(linkCursor.id, linkCursor.link, linkCursor.time, linkCursor.status)
            links.add(link)
        }
        linksListAdapter.data = links
    }

    override fun onItemClicked(position: Int) {
        linksListAdapter.getItem(position)?.let {
            val intent = Intent( "com.example.radmushroom.bigdig_testtask_b")
            intent.putExtra("id", it.id)
            intent.putExtra("link", it.link)
            intent.putExtra("status", it.status)
            startActivity(intent)
        }
    }

    override fun onSortOrderSelected(sortOrder: LinkListAdapter.SortOrder) {
        linksListAdapter.sort(sortOrder)
    }
}
