package com.mobile.todoapplication

import com.mobile.todoapplication.mapper.TodoEntityMapper
import com.mobile.todoapplication.ui.task_all_todo.TodoAllTaskViewModel
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.component.get
import org.koin.core.qualifier.named

/***
 * Created by Phạm Sơn on 6/14/2022
 * Copyright (c) 2022 Propzy. All rights reserved.
 * Email: pson2900@gmail.com
 */
@RunWith(JUnit4::class)
class DatabaseUnitTest : BaseUTTest() {

    @Test
    fun insertData() {
        val viewModel = TodoAllTaskViewModel(getUserCase = get(named(Constraint.GET_TODO_ALL)), mapper = TodoEntityMapper())
        assert(viewModel.todoLiveData.value != null)
//        assertTrue(viewModel.todoLiveData.value != null)
      /*  var todoInsert = TodoSources(
            title = "hello", description = "hom nay",
            createDate = System.currentTimeMillis()
        )
        val viewModel: TodoAllTaskViewModel by inject(qualifier = named("TodoAllTaskViewModel"))
        val testScheduler = TestScheduler()
        Mockito.doReturn(testScheduler)
            .`when`(viewModel)
            .fetchData()
        assertTrue(viewModel.todoLiveData.value != null)*/
    }
}