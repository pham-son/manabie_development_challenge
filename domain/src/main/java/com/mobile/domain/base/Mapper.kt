package com.mobile.domain.base

/***
 * Created by Phạm Sơn on 6/11/2022
 * Copyright (c) 2022 Propzy. All rights reserved.
 * Email: pson2900@gmail.com
 */
abstract class Mapper<T,E>{
    abstract fun toEntity(from: T): E
    abstract fun fromEntity(from: E): T
    fun Flowable(from: T) = io.reactivex.Flowable.fromCallable { toEntity(from) }

    fun Flowable(from: List<T>) = io.reactivex.Flowable.fromCallable { from.map { toEntity(it) } }
}