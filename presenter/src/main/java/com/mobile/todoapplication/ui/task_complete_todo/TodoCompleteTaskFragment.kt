package com.mobile.todoapplication.ui.task_complete_todo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.todoapplication.Constraint.Companion.TAG_DIALOG
import com.mobile.todoapplication.Constraint.Companion.TASK_COMPLETE_VIEW_MODEL
import com.mobile.todoapplication.R
import com.mobile.todoapplication.adapter.TodoAdapter
import com.mobile.todoapplication.databinding.FragmentTodoCompleteBinding
import com.mobile.todoapplication.dialog.DialogDetailTask
import com.mobile.todoapplication.entities.TodoSources
import com.mobile.todoapplication.utils.showDialog
import io.reactivex.Observable
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named


class TodoCompleteTaskFragment : Fragment(), TodoAdapter.TodoRecyclerAdapterListener {
    private val todoAdapter: TodoAdapter by lazy { TodoAdapter(this) }
    private var _binding: FragmentTodoCompleteBinding? = null
    private val todoCompleteTaskViewModel: TodoCompleteTaskViewModel by viewModel(named(TASK_COMPLETE_VIEW_MODEL))

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    fun insert(todoSources: TodoSources) {
        var move = "Pham son movie"
        var moveOb: Observable<String> = Observable.defer {
            Observable.just(move)
        }
        moveOb.subscribe {
            Log.d("QQQ", "$it")
        }

//        todoCompleteTaskViewModel.insert(todoSources)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoCompleteBinding.inflate(inflater, container, false)
        binding.rcTodo.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = todoAdapter
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        todoCompleteTaskViewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressIndicator.show()
            } else {
                binding.progressIndicator.hide()
            }
        }
        todoCompleteTaskViewModel.errorLiveData.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(this@TodoCompleteTaskFragment.requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
        todoCompleteTaskViewModel.todoLiveData.observe(viewLifecycleOwner) { dataResult ->
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
        todoCompleteTaskViewModel.update(item)
    }

    override fun onItemClick(item: TodoSources, position: Int) {
        DialogDetailTask(item).show(this.childFragmentManager, TAG_DIALOG)
    }

    override fun onItemLongClick(item: TodoSources, position: Int) {
        requireContext().showDialog(
            title = this.getString(R.string.common_delete_task),
            message = this.getString(R.string.common_description_task),
            onConfirmed = {todoCompleteTaskViewModel.delete(item)},
        )
    }
}