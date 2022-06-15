package com.mobile.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/***
 * Created by Phạm Sơn on 6/11/2022
 * Copyright (c) 2022 Propzy. All rights reserved.
 * Email: pson2900@gmail.com
 */
@Entity(tableName = "Todo_tb")
class TodoData(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "complete")
    var complete: Int? = 0,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "createDate")
    var createDate: Long? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (other is TodoData) {
            if (other.id != this.id) return false
            if (other.complete != this.complete) return false
            if (other.title != this.title) return false
        }
        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + (complete ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (createDate?.hashCode() ?: 0)
        return result
    }
}