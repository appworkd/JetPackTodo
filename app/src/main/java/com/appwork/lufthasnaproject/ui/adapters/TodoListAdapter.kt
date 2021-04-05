package com.appwork.lufthasnaproject.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.appwork.lufthasnaproject.databinding.ItemTodoBinding
import com.appwork.lufthasnaproject.model.TodoModel
import com.appwork.lufthasnaproject.utils.StringVariables.KEY_DELETE
import com.appwork.lufthasnaproject.utils.StringVariables.KEY_GET

class TodoListAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TodoModel>() {

        override fun areItemsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
           return oldItem.todoId==newItem.todoId
        }

        override fun areContentsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
           return oldItem==newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding=ItemTodoBinding.inflate(LayoutInflater.from(parent.context),
        parent,false)
        return TodoVH(
            itemBinding,
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TodoVH -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<TodoModel>) {
        differ.submitList(list)
    }

    inner class TodoVH(
        private val itemViewBinding: ItemTodoBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemViewBinding.root) {

        fun bind(item: TodoModel) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item,KEY_GET)
            }
            itemViewBinding.tvItemTitle.text= differ.currentList[adapterPosition].todoTitle
            itemViewBinding.tvItemSubTitle.text= differ.currentList[adapterPosition].todoSubTitle
            itemViewBinding.ivDetails.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item,KEY_DELETE)
            }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: TodoModel,action:String)
    }
}