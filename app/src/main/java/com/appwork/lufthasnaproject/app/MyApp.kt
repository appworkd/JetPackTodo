package com.appwork.lufthasnaproject.app

import android.app.Application
import com.appwork.lufthasnaproject.database.TodoDb
import com.appwork.lufthasnaproject.repo.TodoRepository
import com.appwork.lufthasnaproject.ui.factory.TodoVmFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MyApp : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@MyApp))
        bind() from singleton { TodoDb(instance()) }
        bind() from singleton { TodoRepository(instance()) }
        bind() from provider { TodoVmFactory(instance()) }

    }
}