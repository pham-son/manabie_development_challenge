package com.mobile.todoapplication.ui.task_all_todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.todoapplication.Constraint.Companion.TASK_ALL_VIEW_MODEL
import com.mobile.todoapplication.R
import com.mobile.todoapplication.adapter.TodoAdapter
import com.mobile.todoapplication.databinding.FragmentTodoAllTaskBinding
import com.mobile.todoapplication.dialog.DialogDetailTask
import com.mobile.todoapplication.entities.TodoSources
import com.mobile.todoapplication.utils.showDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class TodoAllTaskFragment : Fragment(), TodoAdapter.TodoRecyclerAdapterListener {
    private val todoAdapter: TodoAdapter by lazy { TodoAdapter(this) }

    fun insert(todoSources: TodoSources) {
        todoAllTaskViewModel.insert(todoSources)
    }

    private var _binding: FragmentTodoAllTaskBinding? = null
    private val todoAllTaskViewModel: TodoAllTaskViewModel by viewModel(qualifier = named(TASK_ALL_VIEW_MODEL))

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoAllTaskBinding.inflate(inflater, container, false)
        binding.rcTodo.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = todoAdapter
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        todoAllTaskViewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressIndicator.show()
            } else {
                binding.progressIndicator.hide()
            }
        }

        todoAllTaskViewModel.errorLiveData.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(this@TodoAllTaskFragment.requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
            }
        }

        todoAllTaskViewModel.todoLiveData.observe(viewLifecycleOwner) { dataResult ->
            dataResult?.let {
                todoAdapter.notifyChangedDiffUtil(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemChange(item: TodoSources, position: Int) {
        todoAllTaskViewModel.update(item)
    }

    override fun onItemClick(item: TodoSources, position: Int) {
        DialogDetailTask(item).show(this.childFragmentManager, "DialogTAG")
    }

    override fun onItemLongClick(item: TodoSources, position: Int) {
        requireContext().showDialog(
            title = this.getString(R.string.common_delete_task),
            message = this.getString(R.string.common_description_task),
            onConfirmed = {todoAllTaskViewModel.delete(item)},
        )
    }
}