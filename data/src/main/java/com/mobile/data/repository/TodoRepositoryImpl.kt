package com.mobile.data.repository

import com.mobile.data.db.TodoDao
import com.mobile.data.db.TodoDatabase
import com.mobile.data.entity.mapper.TodoMapper
import com.mobile.domain.entities.TodoEntity
import com.mobile.domain.repositories.TodoRepository
import io.reactivex.Completable
import io.reactivex.Flowable

/***
 * Created by Phạm Sơn on 6/11/2022
 * Copyright (c) 2022 Propzy. All rights reserved.
 * Email: pson2900@gmail.com
 */
class TodoRepositoryImpl(private val database: TodoDatabase) : TodoRepository {
    private val dao: TodoDao = database.getTodoDao()
    override fun getTodoAll(): Flowable<List<TodoEntity>> {
        return dao.getTaskAll().map {
            TodoMapper.fromEntities(it)
        }
    }

    override fun getTodoComplete(): Flowable<List<TodoEntity>> {
        return dao.getTaskComplete().map {
            TodoMapper.fromEntities(it)
        }
    }

    override fun getTodoInComplete(): Flowable<List<TodoEntity>> {
        return dao.getTaskInComplete().map {
            TodoMapper.fromEntities(it)
        }
    }

    override fun insert(todoEntity: TodoEntity?): Completable{
        try {
            todoEntity?.let {
               return dao.insert(TodoMapper.toEntity(todoEntity))
            } ?: return Completable.error(Error("todoEntity == null"))
        }catch (e: Exception){
            return Completable.error(e)
        }
    }

    override fun update(todoEntity: TodoEntity?): Completable {
        try {
            todoEntity?.let {
                return dao.update(it.complete ?: 1, it.id)
            } ?: return Completable.error(Error("todoEntity == null"))
        }catch (e: Exception){
            return Completable.error(e)
        }
    }

    override fun delete(todoEntity: TodoEntity?): Completable {
        try {
            todoEntity?.let {
                return dao.delete(it.id)
            } ?: return Completable.error(Error("todoEntity == null"))
        }catch (e: Exception){
            return Completable.error(e)
        }

    }
}