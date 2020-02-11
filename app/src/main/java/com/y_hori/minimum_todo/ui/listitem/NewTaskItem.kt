package com.y_hori.minimum_todo.ui.listitem

import com.xwray.groupie.databinding.BindableItem
import com.y_hori.minimum_todo.R
import com.y_hori.minimum_todo.data.model.Task
import com.y_hori.minimum_todo.databinding.ListItemNewTaskBinding

class NewTaskItem(private val task: Task):BindableItem<ListItemNewTaskBinding>(){
    override fun getLayout(): Int = R.layout.list_item_new_task

    override fun bind(viewBinding: ListItemNewTaskBinding, position: Int) {
        viewBinding.task = task
    }

}

