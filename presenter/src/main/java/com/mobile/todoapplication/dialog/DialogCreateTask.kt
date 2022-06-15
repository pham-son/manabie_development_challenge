package com.mobile.todoapplication.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mobile.todoapplication.R
import com.mobile.todoapplication.databinding.DialogCreateTaskBinding
import com.mobile.todoapplication.entities.TodoSources

/***
 * Created by Phạm Sơn on 6/12/2022
 * Copyright (c) 2022 Propzy. All rights reserved.
 * Email: pson2900@gmail.com
 */
class DialogCreateTask(private var listener: DialogListener) : DialogFragment() {
    interface DialogListener {
        fun onAccept(todoSources: TodoSources)
    }

    private var _binding: DialogCreateTaskBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogCreateTaskBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnOk.setOnClickListener {
            if (binding.editTitle.text.toString().isEmpty()) {
                binding.editTitle.error = this.getString(R.string.common_not_empty)
            }else {
                val todoSources = TodoSources(
                    title = binding.editTitle.text.toString(),
                    description = binding.editDescription.text.toString(),
                    createDate = System.currentTimeMillis(),
                    complete = 1
                )
                listener.onAccept(todoSources)
            }
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}