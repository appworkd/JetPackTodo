package com.appwork.lufthasnaproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.appwork.lufthasnaproject.model.TodoModel

@Database(entities = [TodoModel::class], version = 1,exportSchema = false)
abstract class TodoDb : RoomDatabase() {
    abstract fun getTodoDao(): TodoDao

    companion object {
        @Volatile //So that only one thread can access this variable
        private var instance: TodoDb? = null
        private val LOCK = Any()

        //@invoke method always triggers when we write like TodoDb()
        operator fun invoke(context: Context) =
            instance ?: synchronized(LOCK) {//So that only one thread can instantiate db
                instance ?: createDatabase(context)
                    .also {
                        instance = it
                    }
            }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            TodoDb::class.java, "Todo.db"
        ).build()
    }
}