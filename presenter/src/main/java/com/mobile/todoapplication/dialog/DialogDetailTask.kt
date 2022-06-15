package com.mobile.todoapplication.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mobile.todoapplication.databinding.DialogDetailTaskBinding
import com.mobile.todoapplication.entities.TodoSources
import com.mobile.todoapplication.utils.formatTimeDate
import com.mobile.todoapplication.utils.toCalendar

/***
 * Created by Phạm Sơn on 6/12/2022
 * Copyright (c) 2022 Propzy. All rights reserved.
 * Email: pson2900@gmail.com
 */
class DialogDetailTask(private var todoSources: TodoSources) : DialogFragment() {
    private var binding: DialogDetailTaskBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogDetailTaskBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnOk?.setOnClickListener {
            dismiss()
        }
        binding?.textChildTitle?.text = "Title: ${todoSources.title}"
        binding?.textDescription?.text = "Description: ${todoSources.description}"
        binding?.textDate?.text = "Create task: ${todoSources.createDate?.toCalendar()?.formatTimeDate()}"
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}