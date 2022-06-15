package com.mobile.todoapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobile.todoapplication.base.BaseRecyclerAdapter
import com.mobile.todoapplication.databinding.ItemTodoBinding
import com.mobile.todoapplication.entities.TodoSources
import com.mobile.todoapplication.utils.onDebounceClick
import com.mobile.todoapplication.utils.toArrayList

/***
 * Created by Phạm Sơn on 6/12/2022
 * Copyright (c) 2022 Propzy. All rights reserved.
 * Email: pson2900@gmail.com
 */
class TodoAdapter(private val listener: TodoRecyclerAdapterListener? = null) : BaseRecyclerAdapter<TodoSources, ItemTodoBinding>() {
    interface TodoRecyclerAdapterListener {
        fun onItemChange(item: TodoSources, position: Int)
        fun onItemClick(item: TodoSources, position: Int)
        fun onItemLongClick(item: TodoSources, position: Int)
    }

    fun notifyChangedDiffUtil(newList: List<TodoSources>) {
        val oldList = dataSet
        dataSet = newList.toArrayList()
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = oldList[oldItemPosition]
                val newItem = newList[newItemPosition]
                return oldItem.compareForDashboardAdapter(newItem)
            }

            override fun getOldListSize(): Int = oldList.size
            override fun getNewListSize(): Int = newList.size
            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = oldList[oldItemPosition]
                val newItem = newList[newItemPosition]
                return oldItem.compareForDashboardAdapter(newItem)
            }
        })
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateView(parent: ViewGroup, viewType: Int): BaseViewHolder<ItemTodoBinding> {
        val itemBinding = ItemTodoBinding.inflate(LayoutInflater.from(context), parent, false)
        val viewHolder = BaseViewHolder(itemBinding)
        /* Register Event Handler */
        viewHolder.binding.switchTodo.onDebounceClick {
            val position = viewHolder.adapterPosition
            if (position == RecyclerView.NO_POSITION) return@onDebounceClick
            val data = dataSet[position]
            if (data.complete == 1) {
                viewHolder.binding.switchTodo.isChecked = true
                data.complete = 0
            } else {
                viewHolder.binding.switchTodo.isChecked = false
                data.complete = 1
            }
            listener?.onItemChange(data, position)
        }
        viewHolder.binding.textTitle.onDebounceClick {
            val position = viewHolder.adapterPosition
            if (position == RecyclerView.NO_POSITION) return@onDebounceClick
            val data = dataSet[position]
            listener?.onItemClick(data, position)
        }
        viewHolder.binding.textTitle.setOnLongClickListener {
            val position = viewHolder.adapterPosition
            if (position == RecyclerView.NO_POSITION) return@setOnLongClickListener false
            val data = dataSet[position]
            listener?.onItemLongClick(data, position)
            true
        }
        return viewHolder
    }

    override fun onBindView(item: TodoSources, position: Int, viewHolder: BaseRecyclerAdapter.BaseViewHolder<ItemTodoBinding>) {
        viewHolder.binding.apply {
            textTitle.text = item.title
            switchTodo.isChecked = item.complete == 0
        }
    }

}