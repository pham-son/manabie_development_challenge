package com.mobile.todoapplication

import android.app.Application
import com.mobile.todoapplication.di.localModules
import com.mobile.todoapplication.di.repositoryModules
import com.mobile.todoapplication.di.userCaseModules
import com.mobile.todoapplication.di.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/***
 * Created by Phạm Sơn on 6/11/2022
 * Copyright (c) 2022 Propzy. All rights reserved.
 * Email: pson2900@gmail.com
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        startKoin {
            androidContext(this@App)
            androidLogger(level = Level.DEBUG)
            modules(
                listOf(
                    localModules,
                    viewModels,
                    repositoryModules,
                    userCaseModules,
                )
            )
        }

    }
}
