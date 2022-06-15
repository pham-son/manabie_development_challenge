package com.mobile.todoapplication.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

/***
 * Created by Phạm Sơn on 6/12/2022
 * Copyright (c) 2022 Propzy. All rights reserved.
 * Email: pson2900@gmail.com
 */
abstract class BaseFragment<VM : ViewModel, VB : ViewBinding> : Fragment() {
    //region Variables
    var binding: VB? = null
//    abstract val viewModel: VM
    abstract fun provideBinding(inflater: LayoutInflater, container: ViewGroup?): VB?

    /***
     *   Setup UI on the first time you launch this screen
     */
    abstract fun setupView()

    /***
     *   Setup UI observe
     */
    abstract fun setupObserveView()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.let {
        setupBinding(inflater, container).apply {
            setupView()
            setupObserveView()
        }
    } ?: binding!!.root

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    //region Private Support Methods
    private fun setupBinding(inflater: LayoutInflater, container: ViewGroup?): View? {
        binding = provideBinding(inflater, container)
        return binding?.root
    }

    //region Update UI

}