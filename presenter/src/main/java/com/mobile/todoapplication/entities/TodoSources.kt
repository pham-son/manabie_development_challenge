package com.mobile.todoapplication.entities

/***
 * Created by Phạm Sơn on 6/11/2022
 * Copyright (c) 2022 Propzy. All rights reserved.
 * Email: pson2900@gmail.com
 */
data class TodoSources(
    var id: Int? = 0,
    var complete: Int? = 0,
    var title: String? = null,
    var description: String? = null,
    var createDate: Long? = null,
){
    fun compareForDashboardAdapter(other: TodoSources): Boolean {
        if (other.id != this.id) return false
        if (other.complete != this.complete) return false
        if (other.title != this.title) return false
        return true
    }
}