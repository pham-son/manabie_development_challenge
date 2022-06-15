package com.mobile.domain.entities

/***
 * Created by Phạm Sơn on 6/11/2022
 * Copyright (c) 2022 Propzy. All rights reserved.
 * Email: pson2900@gmail.com
 */
data class TodoEntity(
    var id: Int,
    var complete: Int? = 0,
    var title: String? = null,
    var description: String? = null,
    var createDate: Long? = null,
)