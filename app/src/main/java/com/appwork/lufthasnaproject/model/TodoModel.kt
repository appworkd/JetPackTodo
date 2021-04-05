package com.appwork.lufthasnaproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "TABLE_TODO")
data class TodoModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "COLUMN_TODO_ID")
    val todoId:Long=0,
    @ColumnInfo(name = "COLUMN_TODO_TITLE")
     var todoTitle:String="",
    @ColumnInfo(name = "COLUMN_DATE")
     var dateInMillis:Long=0L,
    @ColumnInfo(name = "COLUMN_TODO_SUB_TITLE")
     var todoSubTitle:String=""
):Serializable