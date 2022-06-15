package com.mobile.todoapplication.ui.task_all_todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobile.domain.base.Mapper
import com.mobile.domain.entities.TodoEntity
import com.mobile.domain.usercase.GetTodoAllUserCase
import com.mobile.todoapplication.base.BaseViewModel
import com.mobile.todoapplication.entities.Error
import com.mobile.todoapplication.entities.TodoSources
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TodoAllTaskViewModel(
    private val getUserCase: GetTodoAllUserCase,
    private val mapper: Mapper<TodoEntity, TodoSources>
) : BaseViewModel() {
    init {
//        fetchData()
    }

    fun insert(todoSources: TodoSources) {
        val inset = getUserCase.insert(mapper.fromEntity(todoSources))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete { fetchData() }
            .doOnError { getError().postValue(Error(it.message)) }
            .doOnSubscribe { getLoading().postValue(true) }
            .subscribe()
        addDisposable(inset)
    }

    fun update(todoSources: TodoSources) {
        val update = getUserCase.update(mapper.fromEntity(todoSources))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { getLoading().postValue(true) }
            .doOnComplete { fetchData() }.subscribe()
        addDisposable(update)
    }

    fun delete(todoSources: TodoSources) {
        val update = getUserCase.delete(mapper.fromEntity(todoSources))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { getLoading().postValue(true) }
            .doOnError { getError().postValue(Error(it.message)) }
            .doOnComplete { fetchData() }.subscribe()
        addDisposable(update)
    }

    fun fetchData() {
        val disposable = getUserCase.getTodos()
            .concatMap { mapper.Flowable(it) }
            .doOnSubscribe { getLoading().postValue(true) }
            .doOnError { getError().postValue(Error(it.message)) }
            .subscribe { response ->
                _todoLiveData.value = response
                getLoading().postValue(false)
            }
        addDisposable(disposable)
    }

    private val _todoLiveData = MutableLiveData<List<TodoSources>?>()
    val todoLiveData: LiveData<List<TodoSources>?> = _todoLiveData
    val errorLiveData: LiveData<Error?> = getError()
    val loadingLiveData: LiveData<Boolean> = getLoading()
}