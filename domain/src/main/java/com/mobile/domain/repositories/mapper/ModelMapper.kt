package com.mobile.domain.repositories.mapper

/***
 * Created by Phạm Sơn on 6/11/2022
 * Copyright (c) 2022 Propzy. All rights reserved.
 * Email: pson2900@gmail.com
 */
interface ModelMapper<in E, M> {
    interface MappingToModel<E, M> {
        fun toEntity(model: M): E
        fun fromEntity(entity: E): M
    }

    interface MappingToListModel<E, M> {
        fun fromEntities(entities: List<E>): List<M>
        fun toEntities(models: List<M>): List<E>
    }
}