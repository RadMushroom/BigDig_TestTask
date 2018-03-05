package com.example.radmushroom.bigdig_testtask_A.adapter

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<in Model> (itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(model: Model)
}