package com.mobile.domain.repositories

import com.mobile.domain.entities.TodoEntity
import io.reactivex.Completable
import io.reactivex.Flowable

/***
 * Created by Phạm Sơn on 6/11/2022
 * Copyright (c) 2022 Propzy. All rights reserved.
 * Email: pson2900@gmail.com
 */
interface TodoRepository {
    fun getTodoAll(): Flowable<List<TodoEntity>>
    fun getTodoComplete(): Flowable<List<TodoEntity>>
    fun getTodoInComplete(): Flowable<List<TodoEntity>>
    fun insert(todoEntity: TodoEntity?): Completable
    fun update(todoEntity: TodoEntity?): Completable
    fun delete(todoEntity: TodoEntity?): Completable
}