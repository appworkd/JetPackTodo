@file:Suppress("UNCHECKED_CAST")

package com.appwork.lufthasnaproject.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appwork.lufthasnaproject.repo.TodoRepository
import com.appwork.lufthasnaproject.ui.viewmodel.TodoVM

class TodoVmFactory(
    private val repo: TodoRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TodoVM(repo) as T
    }
}
