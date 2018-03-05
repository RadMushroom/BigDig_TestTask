package com.example.radmushroom.bigdig_testtask_A.adapter

import android.support.v7.widget.RecyclerView
import java.util.*


abstract class BaseRecyclerViewAdapter<Model> : RecyclerView.Adapter<BaseViewHolder<Model>>() {

    var data: MutableList<Model> = arrayListOf()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: BaseViewHolder<Model>, position: Int) {
        holder.bind(data[position])
    }

    fun addItems(data: List<Model>) {
        val startIndex = this.data.size
        Collections.copy(this.data, data)
        notifyItemRangeInserted(startIndex, data.size)
    }

    fun addItem(item: Model) {
        data.add(item)
        notifyItemInserted(data.size - 1)
    }

    fun addItem(item: Model, position: Int) {
        if (isValidRange(position, true)) {
            data.add(position, item)
            notifyItemInserted(position)
        }
    }

    fun updateItem(position: Int, item: Model) {
        if (isValidRange(position)) {
            data[position] = item
            notifyItemChanged(position)
        }
    }

    fun getItem(position: Int): Model? {
        return if (isValidRange(position)) {
            data[position]
        } else {
            null
        }
    }

    /*
    * O(n) complexity!
    */
    fun removeItem(item: Model) {
        val position = data.indexOf(item)
        removeItem(position)
    }

    fun removeItem(position: Int) {
        if (isValidRange(position)) {
            data.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    private fun isValidRange(position: Int, insertion: Boolean = false): Boolean {
        val endRange = if (insertion) data.size + 1 else data.size
        return position in 0 until endRange
    }

}