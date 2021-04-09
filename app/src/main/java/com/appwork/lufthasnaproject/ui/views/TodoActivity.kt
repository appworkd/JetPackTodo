package com.appwork.lufthasnaproject.ui.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.appwork.lufthasnaproject.databinding.ActivityTodoBinding
import com.appwork.lufthasnaproject.model.TodoModel
import com.appwork.lufthasnaproject.ui.adapters.TodoListAdapter
import com.appwork.lufthasnaproject.ui.adapters.TodoListAdapter.Interaction
import com.appwork.lufthasnaproject.ui.factory.TodoVmFactory
import com.appwork.lufthasnaproject.ui.viewmodel.TodoVM
import com.appwork.lufthasnaproject.utils.StringVariables.KEY_CURRENT_ITEM
import com.appwork.lufthasnaproject.utils.StringVariables.KEY_DELETE
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.util.*

class TodoActivity : AppCompatActivity(), LifecycleOwner, Interaction, KodeinAware {
    override val kodein by kodein()
    private val factory: TodoVmFactory by instance()

    private lateinit var todoVm: TodoVM
    private lateinit var mainBinding: ActivityTodoBinding
    private lateinit var todoAdapter: TodoListAdapter
    private var todoList = arrayListOf<TodoModel>()
    private val channel = Channel<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityTodoBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        todoAdapter = TodoListAdapter(this)
        todoVm = ViewModelProvider(this, factory).get(TodoVM::class.java)
        setUpRecyclerView()
        this.lifecycle.addObserver(todoVm)
        mainBinding.fabAddTodo.setOnClickListener {
            val intent = Intent(this, AddTodoActivity::class.java)
            startActivity(intent)
        }
        todoVm.getAllToDos().observe(this, {
            Log.i("TAG", "Todo List : ${it.size} ")
            if (it.isNotEmpty()) {
                todoList = it as ArrayList<TodoModel>
                todoAdapter.submitList(it.sorted())
            } else {
                todoList.clear()
                todoAdapter.submitList(it)
            }
            todoAdapter.notifyDataSetChanged()
        })
    }

    override fun onResume() {
        super.onResume()
    }

    private fun setUpRecyclerView() {
        mainBinding.todoListContainer.rvAllTodo.apply {
            this.layoutManager = LinearLayoutManager(
                this@TodoActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            this.adapter = todoAdapter
            this.setHasFixedSize(true)
        }
    }

    override fun onItemSelected(position: Int, item: TodoModel, action: String) {
        if (action == KEY_DELETE) {
            todoVm.deleteTodo(item)
        } else {
            val intent = Intent(this, AddTodoActivity::class.java)
                .also {
                    it.putExtra(KEY_CURRENT_ITEM, item)
                }
            startActivity(intent)
        }
    }
}