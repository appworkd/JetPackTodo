package com.appwork.lufthasnaproject.ui.views

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.appwork.lufthasnaproject.R
import com.appwork.lufthasnaproject.databinding.ActivityAddTodoBinding
import com.appwork.lufthasnaproject.model.TodoModel
import com.appwork.lufthasnaproject.ui.factory.TodoVmFactory
import com.appwork.lufthasnaproject.ui.viewmodel.TodoVM
import com.appwork.lufthasnaproject.utils.StringVariables.KEY_CURRENT_ITEM
import com.appwork.lufthasnaproject.utils.showSnackBar
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

        bindingAddTodoActivity.btnAdd.setOnClickListener {
            val titleVal = bindingAddTodoActivity.etTitle.text.toString()
            val subTitleVal = bindingAddTodoActivity.etDetails.text.toString()
            if (titleVal.isNotEmpty() && subTitleVal.isNotEmpty()) {
                //Todo call db and insert item
                var intPriority = 4
                if (bindingAddTodoActivity.spinnerPriority.selectedItemPosition != 0) {
                    intPriority =
                        bindingAddTodoActivity.spinnerPriority.selectedItem.toString().toInt()
                }
                if (todoItem == null) {
                    val todo = TodoModel(
                        todoTitle = titleVal,
                        todoSubTitle = subTitleVal,
                        todoPriority = intPriority
                    )
                    vmTodo.insertTodo(todo)
                } else {
                    val updatedTodo = TodoModel(
                        todoId = todoItem!!.todoId,
                        todoTitle = titleVal,
                        todoSubTitle = subTitleVal,
                        todoPriority = intPriority
                    )
                    vmTodo.updateCurrentItem(updatedTodo)
                }
                finish()
            } else {
                bindingAddTodoActivity.addTodoParent.showSnackBar(
                    "Values can't be empty"
                )
            }
        }

        bindingAddTodoActivity.spinnerPriority.apply {
            this.adapter = ArrayAdapter(
                this@AddTodoActivity,
                android.R.layout.simple_list_item_1,
                resources.getStringArray(R.array.SelectPriority)
            )
        }
        if (intent.hasExtra(KEY_CURRENT_ITEM)) {
            todoItem = intent.getSerializableExtra(KEY_CURRENT_ITEM) as TodoModel
            todoItem?.let {
                bindingAddTodoActivity.etTitle.setText(it.todoTitle)
                bindingAddTodoActivity.etDetails.setText(it.todoSubTitle)
                bindingAddTodoActivity.spinnerPriority.setSelection((it.todoPriority))
            }
        }
    }

    override fun onResume() {
        super.onResume()

    }
}