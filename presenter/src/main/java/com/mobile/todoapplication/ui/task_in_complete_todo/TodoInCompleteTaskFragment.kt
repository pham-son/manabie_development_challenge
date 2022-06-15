package com.mobile.todoapplication.ui.task_in_complete_todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.todoapplication.Constraint.Companion.TASK_IN_COMPLETE_VIEW_MODEL
import com.mobile.todoapplication.R
import com.mobile.todoapplication.adapter.TodoAdapter
import com.mobile.todoapplication.databinding.FragmentTodoIncompleteBinding
import com.mobile.todoapplication.dialog.DialogDetailTask
import com.mobile.todoapplication.entities.TodoSources
import com.mobile.todoapplication.utils.showDialog
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.qualifier.named

class TodoInCompleteTaskFragment : Fragment(), TodoAdapter.TodoRecyclerAdapterListener {
    private val todoAdapter: TodoAdapter by lazy { TodoAdapter(this) }
    private var _binding: FragmentTodoIncompleteBinding? = null
    private val todoInCompleteTaskViewModel by sharedViewModel<TodoInCompleteTaskViewModel>(named(TASK_IN_COMPLETE_VIEW_MODEL))
    private val binding get() = _binding!!

    fun insert(todoSources: TodoSources) {
        todoInCompleteTaskViewModel.insert(todoSources)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoIncompleteBinding.inflate(inflater, container, false)
        binding.rcTodo.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = todoAdapter
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        todoInCompleteTaskViewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressIndicator.show()
            } else {
                binding.progressIndicator.hide()
            }
        }

        todoInCompleteTaskViewModel.errorLiveData.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(this@TodoInCompleteTaskFragment.requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
            }
        }

        todoInCompleteTaskViewModel.todoLiveData.observe(viewLifecycleOwner) { dataResult ->
            dataResult?.let { todoAdapter.notifyChangedDiffUtil(it) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemChange(item: TodoSources, position: Int) {
        todoInCompleteTaskViewModel.update(item)
    }

    override fun onItemClick(item: TodoSources, position: Int) {
        DialogDetailTask(item).show(this.childFragmentManager, "DialogTAG")
    }

    override fun onItemLongClick(item: TodoSources, position: Int) {
        requireContext().showDialog(
            title = this.getString(R.string.common_delete_task),
            message = this.getString(R.string.common_description_task),
            onConfirmed = {todoInCompleteTaskViewModel.delete(item)},
        )
    }
}