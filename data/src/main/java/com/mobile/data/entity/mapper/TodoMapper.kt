package com.mobile.data.entity.mapper

import com.mobile.data.entity.TodoData
import com.mobile.domain.entities.TodoEntity
import com.mobile.domain.repositories.mapper.ModelMapper

/***
 * Created by Phạm Sơn on 6/11/2022
 * Copyright (c) 2022 Propzy. All rights reserved.
 * Email: pson2900@gmail.com
 */
object TodoMapper :
    ModelMapper.MappingToModel<TodoData, TodoEntity>,
    ModelMapper.MappingToListModel<TodoData, TodoEntity> {
    override fun toEntity(model: TodoEntity) = TodoData(
        complete = model.complete,
        title = model.title,
        description = model.description,
        createDate = model.createDate
    )

    override fun fromEntity(entity: TodoData) = TodoEntity(
        id = entity.id,
        complete = entity.complete,
        title = entity.title,
        description = entity.description,
        createDate = entity.createDate
    )

    override fun fromEntities(entities: List<TodoData>): List<TodoEntity> {
        val models = mutableListOf<TodoEntity>()
        entities.map { models.add(fromEntity(it)) }
        return models
    }

    override fun toEntities(models: List<TodoEntity>): List<TodoData> {
        val entities = mutableListOf<TodoData>()
        models.map { entities.add(toEntity(it)) }
        return entities
    }

}