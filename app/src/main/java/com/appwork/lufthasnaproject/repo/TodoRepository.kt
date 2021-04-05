package com.appwork.lufthasnaproject.repo

import com.appwork.lufthasnaproject.database.TodoDb
import com.appwork.lufthasnaproject.model.TodoModel

class TodoRepository(private val db: TodoDb) {
    suspend fun insertTodo(todo: TodoModel) = db.getTodoDao().insertTodo(todo)
    suspend fun updateTodo(todo: TodoModel) = db.getTodoDao().updateCurrentTodo(todo)
    suspend fun deleteTodo(todo: TodoModel) = db.getTodoDao().deleteTodo(todo)
     fun getAllTodo() = db.getTodoDao().getAllToDos()
     fun getSingleTodo(id: Long) = db.getTodoDao().getSingleTodoWithId(todoId = id)
}