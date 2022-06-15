package com.mobile.todoapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.mobile.todoapplication.Constraint.Companion.TAG_DIALOG
import com.mobile.todoapplication.databinding.ActivityMainBinding
import com.mobile.todoapplication.dialog.DialogCreateTask
import com.mobile.todoapplication.entities.TodoSources
import com.mobile.todoapplication.ui.task_all_todo.TodoAllTaskFragment
import com.mobile.todoapplication.ui.task_complete_todo.TodoCompleteTaskFragment
import com.mobile.todoapplication.ui.task_in_complete_todo.TodoInCompleteTaskFragment

class MainActivity : AppCompatActivity(), DialogCreateTask.DialogListener {
    private lateinit var navHostFragment: NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        binding.toolBar.apply {
            val appBarConfiguration = AppBarConfiguration(
                topLevelDestinationIds = setOf(
                    R.id.navigation_todo_all_task, R.id.navigation_todo_complete_task, R.id.navigation_todo_in_complete_task
                )
            )
            setupWithNavController(navHostFragment.navController, appBarConfiguration)
            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.plus_todo -> {
                        DialogCreateTask(this@MainActivity).show(this@MainActivity.supportFragmentManager, TAG_DIALOG)
                    }
                    else -> {}
                }
                true
            }
        }
        binding.navView.setupWithNavController(navHostFragment.navController)
    }

    override fun onAccept(todoSources: TodoSources) {
        when (val currentFragment = navHostFragment.childFragmentManager.fragments[0]) {
            is TodoAllTaskFragment -> {
                currentFragment.insert(todoSources)
            }
            is TodoCompleteTaskFragment -> {
                currentFragment.insert(todoSources)
            }
            is TodoInCompleteTaskFragment -> {
                currentFragment.insert(todoSources)
            }
        }
        supportFragmentManager.findFragmentByTag(TAG_DIALOG)?.let {
            (it as DialogCreateTask).dismiss()
        }
    }
}