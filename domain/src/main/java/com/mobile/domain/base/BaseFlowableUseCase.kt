package com.mobile.domain.base

import io.reactivex.Completable
import io.reactivex.Flowable

/***
 * Created by Phạm Sơn on 6/11/2022
 * Copyright (c) 2022 Propzy. All rights reserved.
 * Email: pson2900@gmail.com
 */
abstract class BaseFlowableUseCase<T> {
    abstract fun insert(dataInsert: T): Completable
    abstract fun update(dataUpdate: T): Completable
    abstract fun delete(dataDelete: T): Completable
    abstract fun getTodos(): Flowable<List<T>>
}
