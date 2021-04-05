package com.appwork.lufthasnaproject.ui.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.appwork.lufthasnaproject.databinding.ActivityAddTodoBinding
import com.appwork.lufthasnaproject.model.TodoModel
import com.appwork.lufthasnaproject.ui.factory.TodoVmFactory
import com.appwork.lufthasnaproject.ui.viewmodel.TodoVM
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class AddTodoActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: TodoVmFactory by instance()
    private lateinit var bindingAddTodoActivity: ActivityAddTodoBinding
    private lateinit var vmTodo: TodoVM
    private var todoItem: TodoModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingAddTodoActivity = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(bindingAddTodoActivity.root)
        vmTodo = ViewModelProvider(this, factory).get(TodoVM::class.java)
        if (intent.hasExtra("currentItem")) {
            todoItem = intent.getSerializableExtra("currentItem") as TodoModel
            todoItem?.let {
                bindingAddTodoActivity.etTitle.setText(it.todoTitle)
                bindingAddTodoActivity.etDetails.setText(it.todoSubTitle)
            }
        }
        bindingAddTodoActivity.btnAdd.setOnClickListener {
            val titleVal = bindingAddTodoActivity.etTitle.text.toString()
            val subTitleVal = bindingAddTodoActivity.etDetails.text.toString()
            if (titleVal.isNotEmpty() && subTitleVal.isNotEmpty()) {
                //Todo call db and insert item
                if (todoItem == null) {
                    val todo = TodoModel(todoTitle = titleVal, todoSubTitle = subTitleVal)
                    vmTodo.insertTodo(todo)
                } else {
                    val updatedTodo = TodoModel(
                        todoId = todoItem!!.todoId,
                        todoTitle = titleVal,
                        todoSubTitle = subTitleVal
                    )
                    vmTodo.updateCurrentItem(updatedTodo)
                }
                finish()
            }
        }
    }
}