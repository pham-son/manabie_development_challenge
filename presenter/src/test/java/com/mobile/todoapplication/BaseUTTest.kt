package com.mobile.todoapplication

import android.content.Context
import com.mobile.todoapplication.di.localModules
import com.mobile.todoapplication.di.repositoryModules
import com.mobile.todoapplication.di.userCaseModules
import com.mobile.todoapplication.di.viewModels
import org.junit.After
import org.junit.Before
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito.mock
import java.io.File

/***
 * Created by Phạm Sơn on 6/15/2022
 * Copyright (c) 2022 Propzy. All rights reserved.
 * Email: pson2900@gmail.com
 */
open class BaseUTTest: KoinTest {

    /**
     * Default, let server be shut down
     */
    private var mShouldStart = false
    @Mock
    private var context = mock(Context::class.java)

    @Before
    open fun setUp(){
        startMockServer(true)
        startKoin {
            androidContext(context)
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


    /**
     * Reads input file and converts to json
     */
    fun getJson(path : String) : String {
        val uri = javaClass.classLoader!!.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }

    /**
     * Start Mockwebserver
     */
    private fun startMockServer(shouldStart:Boolean){
        if (shouldStart){
            mShouldStart = shouldStart
        }
    }

    @After
    open fun tearDown(){
        //Stop Koin as well
        stopKoin()
    }
}