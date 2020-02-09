package com.y_hori.minimum_todo.ui.adapter

import android.view.View
import androidx.annotation.StringRes
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.databinding.BindableItem
import com.y_hori.minimum_todo.R
import com.y_hori.minimum_todo.databinding.ListItemHeaderBinding

class ExpandableHeaderItem(
    @StringRes private val titleStringResId: Int) : BindableItem<ListItemHeaderBinding>() {

    private lateinit var expandableGroup: ExpandableGroup

    override fun getLayout(): Int {
        return R.layout.list_item_header
    }


    override fun bind(viewBinding: ListItemHeaderBinding, position: Int) {
        viewBinding.title.setText(titleStringResId)

        viewBinding.icon.apply {
            visibility = View.VISIBLE
//            setImageResource(if (expandableGroup.isExpanded) R.drawable.collapse else R.drawable.expand)
            setOnClickListener {
                expandableGroup.onToggleExpanded()
//                bindIcon(viewHolder)
            }
        }
    }
}
