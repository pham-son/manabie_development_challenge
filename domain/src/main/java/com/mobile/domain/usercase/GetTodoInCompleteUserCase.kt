package com.mobile.domain.usercase

import com.mobile.domain.base.BaseFlowableUseCase
import com.mobile.domain.base.FlowableRxTransformer
import com.mobile.domain.entities.TodoEntity
import com.mobile.domain.repositories.TodoRepository
import io.reactivex.Completable
import io.reactivex.Flowable

/***
 * Created by Phạm Sơn on 6/11/2022
 * Copyright (c) 2022 Propzy. All rights reserved.
 * Email: pson2900@gmail.com
 */
class GetTodoInCompleteUserCase(
    private val transformer: FlowableRxTransformer<List<TodoEntity>>,
    private val repositories: TodoRepository
) : BaseFlowableUseCase<TodoEntity>() {
    override fun getTodos(): Flowable<List<TodoEntity>> {
        return repositories.getTodoInComplete().compose(transformer)
    }

    override fun insert(dataInsert: TodoEntity): Completable {
        return repositories.insert(dataInsert)
    }

    override fun update(dataUpdate: TodoEntity): Completable {
        return repositories.update(dataUpdate)
    }

    override fun delete(dataDelete: TodoEntity): Completable {
        return repositories.delete(dataDelete)
    }
}