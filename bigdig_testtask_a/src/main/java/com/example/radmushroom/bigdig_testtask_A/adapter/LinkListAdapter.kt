package com.example.radmushroom.bigdig_testtask_A.adapter

import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.radmushroom.bigdig_testtask_A.R
import com.example.radmushroom.contentprovider.link.LinkModel
import java.text.SimpleDateFormat
import java.util.*


class LinkListAdapter(private val clickListener: OnPositionClickListener): BaseRecyclerViewAdapter<LinkModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<LinkModel> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item_layout, parent, false)
        return LinksListViewHolder(view, clickListener)
    }

    interface OnPositionClickListener{
        fun onItemClicked(position: Int)
    }

    fun sort(sortOrder: SortOrder){
        val old = data.toMutableList()
        when (sortOrder){
            is SortOrder.ByStatus -> data.sortWith(sortOrder)
            is SortOrder.ByDate -> data.sortWith(sortOrder)
        }
        val diffResult = DiffUtil.calculateDiff(LinkDiffUtilCallback(old, data))
        diffResult.dispatchUpdatesTo(this)
    }

    sealed class SortOrder{
        protected val dateFormatter = SimpleDateFormat("hh:mm:ss dd-MM-yyyy", Locale.getDefault())
        object ByStatus: SortOrder(), Comparator<LinkModel> {
            override fun compare(link1: LinkModel, link2: LinkModel): Int {
                return link1.status.compareTo(link2.status)
            }
        }
        object ByDate: SortOrder(), Comparator<LinkModel>{
            override fun compare(link1: LinkModel, link2: LinkModel): Int {
                val data1 = dateFormatter.parse(link1.time)
                val data2 = dateFormatter.parse(link2.time)
                return data1.compareTo(data2)
            }

        }
    }

    class LinkDiffUtilCallback(private val old: List<LinkModel>, private val new: List<LinkModel>): DiffUtil.Callback(){

        override fun getOldListSize(): Int = old.size

        override fun getNewListSize(): Int = new.size


        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition].id == new[newItemPosition].id
        }
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldLink = old[oldItemPosition]
            val newLink = new[newItemPosition]
            return oldLink.link == newLink.link &&
                    oldLink.status == newLink.status &&
                    oldLink.time == newLink.time
        }

    }

    fun removeItemWithId(id: Long) {
        data.first { it.id == id }.let {
            val position = data.indexOf(it)
            removeItem(position)
        }
    }
}