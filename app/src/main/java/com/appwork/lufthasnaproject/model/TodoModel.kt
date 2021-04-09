package com.appwork.lufthasnaproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import java.io.Serializable
import java.util.*
import kotlin.Comparator

@Entity(tableName = "TABLE_TODO")
data class TodoModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "COLUMN_TODO_ID")
    val todoId: Long = 0,
    @ColumnInfo(name = "COLUMN_TODO_TITLE")
    var todoTitle: String = "",
    @ColumnInfo(name = "COLUMN_DATE")
    var dateInMillis: Long = Calendar.getInstance().timeInMillis,
    @ColumnInfo(name = "COLUMN_TODO_SUB_TITLE")
    var todoSubTitle: String = "",
    @ColumnInfo(name = "COLUMN_PRIORY")
    var todoPriority: Int = 4
) : Serializable,Comparable<TodoModel>{
    override fun compareTo(other: TodoModel): Int {
        return  todoPriority-other.todoPriority
    }
}


