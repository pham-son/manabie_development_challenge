package com.mobile.todoapplication.base

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/***
 * Created by Phạm Sơn on 6/12/2022
 * Copyright (c) 2022 Propzy. All rights reserved.
 * Email: pson2900@gmail.com
 */
abstract class BaseRecyclerAdapter<T, VB : ViewBinding>(var dataSet: ArrayList<T> = arrayListOf()) : RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder<VB>>() {

    lateinit var context: Context

    override fun getItemCount() = dataSet.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        context = parent.context
        return onCreateView(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        onBindView(dataSet[position], position, holder)
    }

    protected abstract fun onCreateView(parent: ViewGroup, viewType: Int): BaseViewHolder<VB>

    protected abstract fun onBindView(item: T, position: Int, viewHolder: BaseViewHolder<VB>)

    open class BaseViewHolder<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)

}