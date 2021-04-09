package com.appwork.lufthasnaproject.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.appwork.lufthasnaproject.model.TodoModel

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todoItem: TodoModel): Long

    @Query("SELECT * FROM TABLE_TODO")
    fun getAllToDos(): LiveData<List<TodoModel>>

    @Query("SELECT * FROM TABLE_TODO WHERE COLUMN_TODO_ID IN (:todoId)")
    fun getSingleTodoWithId(todoId: Long): TodoModel

    @Delete
    suspend fun deleteTodo(todo: TodoModel)

    @Update
    suspend fun updateCurrentTodo(todo: TodoModel): Int
}