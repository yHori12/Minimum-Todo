package com.y_hori.minimum_todo.ui.listitem

import android.animation.AnimatorInflater
import android.view.View
import androidx.annotation.StringRes
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.databinding.BindableItem
import com.y_hori.minimum_todo.R
import com.y_hori.minimum_todo.databinding.ListItemHeaderBinding

class ExpandableHeaderItem(
    private val title: String
) : BindableItem<ListItemHeaderBinding>(), ExpandableItem {

    private lateinit var expandableGroup: ExpandableGroup

    override fun getLayout(): Int {
        return R.layout.list_item_header
    }

    override fun bind(viewBinding: ListItemHeaderBinding, position: Int) {
        viewBinding.title.text = title

        viewBinding.icon.apply {
            visibility = View.VISIBLE
            setImageResource(if (expandableGroup.isExpanded) R.drawable.ic_collapse else R.drawable.ic_expand)
            setOnClickListener {
                expandableGroup.onToggleExpanded()
                bindIcon(viewBinding)
            }
        }
    }

    private fun bindIcon(viewBinding: ListItemHeaderBinding) {
        viewBinding.icon.visibility = View.VISIBLE
        AnimatorInflater.loadAnimator(
            viewBinding.root.context,
            (if (expandableGroup.isExpanded) R.animator.rotation_expand_to_collapse else R.animator.rotation_collapse_to_expand)
        ).apply {
            setTarget(viewBinding.icon)
            start()
        }
    }

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        expandableGroup = onToggleListener
    }

}
