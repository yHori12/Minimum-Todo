package com.y_hori.minimum_todo.ui.listitem

import com.xwray.groupie.databinding.BindableItem
import com.y_hori.minimum_todo.R
import com.y_hori.minimum_todo.data.model.Task
import com.y_hori.minimum_todo.databinding.ListItemCompletedTaskBinding
import com.y_hori.minimum_todo.utils.setStrike


class CompletedTaskItem(private val task: Task) :
    BindableItem<ListItemCompletedTaskBinding>() {
    override fun getLayout(): Int =
        R.layout.list_item_completed_task

    override fun bind(viewBinding: ListItemCompletedTaskBinding, position: Int) {
        viewBinding.tvTitle.apply {
            this.text = task.title
            this.paint.setStrike()
        }
    }
}
