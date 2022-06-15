package com.mobile.todoapplication.base

import com.mobile.domain.base.FlowableRxTransformer
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import org.reactivestreams.Publisher

/***
 * Created by Phạm Sơn on 6/11/2022
 * Copyright (c) 2022 Propzy. All rights reserved.
 * Email: pson2900@gmail.com
 */
class AsyncFlowableTransformer<T> : FlowableRxTransformer<T>() {

    override fun apply(upstream: Flowable<T>): Publisher<T> {
        return upstream
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}