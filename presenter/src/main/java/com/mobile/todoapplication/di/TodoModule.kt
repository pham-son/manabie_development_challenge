package com.mobile.todoapplication.di

import androidx.room.Room
import com.mobile.data.db.TodoDatabase
import com.mobile.data.repository.TodoRepositoryImpl
import com.mobile.domain.repositories.TodoRepository
import com.mobile.domain.usercase.GetTodoAllUserCase
import com.mobile.domain.usercase.GetTodoCompleteUserCase
import com.mobile.domain.usercase.GetTodoInCompleteUserCase
import com.mobile.todoapplication.Constraint.Companion.DATABASE
import com.mobile.todoapplication.Constraint.Companion.GET_TODO_ALL
import com.mobile.todoapplication.Constraint.Companion.GET_TODO_COMPLETE
import com.mobile.todoapplication.Constraint.Companion.GET_TODO_INCOMPLETE
import com.mobile.todoapplication.Constraint.Companion.REPOSITORY
import com.mobile.todoapplication.Constraint.Companion.TASK_ALL_VIEW_MODEL
import com.mobile.todoapplication.Constraint.Companion.TASK_COMPLETE_VIEW_MODEL
import com.mobile.todoapplication.Constraint.Companion.TASK_IN_COMPLETE_VIEW_MODEL
import com.mobile.todoapplication.base.AsyncFlowableTransformer
import com.mobile.todoapplication.mapper.TodoEntityMapper
import com.mobile.todoapplication.ui.task_all_todo.TodoAllTaskViewModel
import com.mobile.todoapplication.ui.task_complete_todo.TodoCompleteTaskViewModel
import com.mobile.todoapplication.ui.task_in_complete_todo.TodoInCompleteTaskViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/***
 * Created by Phạm Sơn on 6/11/2022
 * Copyright (c) 2022 Propzy. All rights reserved.
 * Email: pson2900@gmail.com
 */
val repositoryModules = module {
    single(named(REPOSITORY)) {
        TodoRepositoryImpl(database = get(named(DATABASE))) as TodoRepository
    }
}

val userCaseModules = module {
    factory(named(GET_TODO_ALL)) { GetTodoAllUserCase(transformer = AsyncFlowableTransformer(), repositories = get(named(REPOSITORY))) }
    factory(named(GET_TODO_COMPLETE)) { GetTodoCompleteUserCase(transformer = AsyncFlowableTransformer(), repositories = get(named(REPOSITORY))) }
    factory(named(GET_TODO_INCOMPLETE)) { GetTodoInCompleteUserCase(transformer = AsyncFlowableTransformer(), repositories = get(named(REPOSITORY))) }
}

val localModules = module {
    single(named(DATABASE)) {
        Room.databaseBuilder(androidContext(), TodoDatabase::class.java, "Todo_tb").build()
    }
}

val viewModels = module {
    viewModel(qualifier = named(TASK_ALL_VIEW_MODEL)) { TodoAllTaskViewModel(getUserCase = get(named(GET_TODO_ALL)), mapper = TodoEntityMapper())}
    viewModel(qualifier = named(TASK_COMPLETE_VIEW_MODEL)) { TodoCompleteTaskViewModel(getUserCase = get(named(GET_TODO_COMPLETE)), mapper = TodoEntityMapper()) }
    viewModel(qualifier = named(TASK_IN_COMPLETE_VIEW_MODEL)) { TodoInCompleteTaskViewModel(getUserCase = get(named(GET_TODO_INCOMPLETE)), mapper = TodoEntityMapper()) }
}

