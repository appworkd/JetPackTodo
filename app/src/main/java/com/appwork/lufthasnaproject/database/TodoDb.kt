package com.appwork.lufthasnaproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.appwork.lufthasnaproject.model.TodoModel

@Database(entities = [TodoModel::class], version = 2)
//@Database(entities = [TodoModel::class], version = 1)
abstract class TodoDb : RoomDatabase() {
    abstract fun getTodoDao(): TodoDao


    companion object {
        @Volatile //So that only one thread can access this variable
        private var instance: TodoDb? = null
        private val LOCK = Any()
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE TABLE_TODO ADD COLUMN COLUMN_PRIORY INTEGER NOT NULL DEFAULT 0"
                )
            }
        }
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
        )
            .fallbackToDestructiveMigrationOnDowngrade()
             .addMigrations(MIGRATION_1_2)
            .build()
    }
}