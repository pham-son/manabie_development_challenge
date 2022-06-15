package com.mobile.data.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mobile.data.entity.TodoData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TodoDatabaseTest {
    private lateinit var todoDao: TodoDao
    private lateinit var db: TodoDatabase
    private lateinit var compositeDisposable: CompositeDisposable
    @Before
    fun setUp() {
        compositeDisposable = CompositeDisposable()

        val context = ApplicationProvider.getApplicationContext<Context>()
        // initialize the db and dao variable
        db = Room.inMemoryDatabaseBuilder(context, TodoDatabase::class.java).build()
        todoDao = db.getTodoDao()
    }

    @After
    fun tearDown() {
        db.close()
        compositeDisposable.dispose()
    }

    @Test
    fun getAllTodo(){
        val disposable = db.getTodoDao().getTaskAll()
            .observeOn(TestScheduler())
            .subscribeOn(Schedulers.io())
            .subscribe { todos ->
                assert(todos?.isNotEmpty() ?: false)
            }

        compositeDisposable.add(disposable)
    }

    @Test
    fun getTodoDao() {
        val todo = TodoData(id = 30,title = "hôm nay", description = "thứ 7", createDate = System.currentTimeMillis())
        todoDao.insert(todo)
        val todos = db.getTodoDao().getTaskAll()


    }
}