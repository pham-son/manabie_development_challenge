package com.mobile.domain.base

import io.reactivex.FlowableTransformer

/***
 * Created by Phạm Sơn on 6/11/2022
 * Copyright (c) 2022 Propzy. All rights reserved.
 * Email: pson2900@gmail.com
 */
abstract class FlowableRxTransformer<T>: FlowableTransformer<T, T>
abstract class FlowableRxTransformerCouple<I, O>: FlowableTransformer<I, O>