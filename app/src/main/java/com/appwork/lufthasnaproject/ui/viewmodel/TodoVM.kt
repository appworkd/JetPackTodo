package com.appwork.lufthasnaproject.ui.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appwork.lufthasnaproject.model.TodoModel
import com.appwork.lufthasnaproject.repo.TodoRepository
import kotlinx.coroutines.launch

class TodoVM(
    private val repo: TodoRepository
) : ViewModel(), LifecycleObserver {

    fun insertTodo(item: TodoModel) = viewModelScope.launch { repo.insertTodo(item) }

    fun getAllToDos() = repo.getAllTodo()

    fun updateCurrentItem(todo: TodoModel) =
        viewModelScope.launch { repo.updateTodo(todo) }

    fun deleteTodo(todo: TodoModel) = viewModelScope.launch { repo.deleteTodo(todo) }
}