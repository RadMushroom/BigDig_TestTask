package com.example.radmushroom.bigdig_testtask_A.adapter

import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.view.View
import com.example.radmushroom.bigdig_testtask_A.R
import com.example.radmushroom.contentprovider.link.LinkModel
import com.example.radmushroom.contentprovider.link.Status
import kotlinx.android.synthetic.main.history_item_layout.view.*

class LinksListViewHolder(view: View, clickListener: LinkListAdapter.OnPositionClickListener) : BaseViewHolder<LinkModel>(view) {
    init {
        view.setOnClickListener { clickListener.onItemClicked(adapterPosition) }
    }

    override fun bind(model: LinkModel) {
        with(itemView) {
            linkTxt.text = model.link
            timeTxt.text = model.time
            statusTxt.text = model.status.toString()
            when (model.status) {
                Status.STATUS_1 -> setBg(R.color.colorGreen)
                Status.STATUS_2 -> setBg(R.color.colorRed)
                Status.STATUS_3 -> setBg(R.color.colorGrey)
            }
        }
    }

    private fun setBg(@ColorRes colorRes: Int){
        (itemView as CardView).setCardBackgroundColor(ContextCompat.getColor(itemView.context, colorRes))
    }
}