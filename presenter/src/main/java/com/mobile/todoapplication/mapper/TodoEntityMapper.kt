package com.mobile.todoapplication.mapper

import com.mobile.domain.base.Mapper
import com.mobile.domain.entities.TodoEntity
import com.mobile.todoapplication.entities.TodoSources

/***
 * Created by Phạm Sơn on 6/11/2022
 * Copyright (c) 2022 Propzy. All rights reserved.
 * Email: pson2900@gmail.com
 */
class TodoEntityMapper:
    Mapper<TodoEntity, TodoSources>() {

    override fun toEntity(model: TodoEntity) = TodoSources(
        id = model.id,
        complete = model.complete,
        title = model.title,
        description = model.description,
        createDate = model.createDate,
    )

    override fun fromEntity(from: TodoSources) = TodoEntity (
        id = from.id ?: -1,
        complete = from.complete,
        title = from.title,
        description = from.description,
        createDate = from.createDate,
    )
}