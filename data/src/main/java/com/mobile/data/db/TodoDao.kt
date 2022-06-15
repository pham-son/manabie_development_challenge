package com.mobile.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobile.data.entity.TodoData
import io.reactivex.Completable
import io.reactivex.Flowable

/***
 * Created by Phạm Sơn on 6/11/2022
 * Copyright (c) 2022 Propzy. All rights reserved.
 * Email: pson2900@gmail.com
 */
@Dao
interface TodoDao {
    /**
     * get all TodoData
     */
    @Query("Select * from Todo_tb order by createDate")
    fun getTaskAll(): Flowable<List<TodoData>?>

    /**
     *  get complete TodoData
     */
    @Query("Select * from Todo_tb where complete == 0 order by createDate")
    fun getTaskComplete(): Flowable<List<TodoData>?>

    /**
     *  get in complete TodoData
     */
    @Query("Select * from Todo_tb where complete == 1 order by createDate")
    fun getTaskInComplete(): Flowable<List<TodoData>?>

    /**
     * insert TodoData
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todo: TodoData): Completable

    /**
     * Updating object TodoData
     */
    @Query("UPDATE todo_tb SET complete=:complete WHERE id =:id")
    fun update(complete: Int, id: Int): Completable

    /**
     * Delete object TodoData
     */
    @Query("DELETE FROM todo_tb WHERE id =:id")
    fun delete(id: Int): Completable

}