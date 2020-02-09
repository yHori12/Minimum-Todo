package com.y_hori.minimum_todo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.xwray.groupie.*
import com.y_hori.minimum_todo.R
import com.y_hori.minimum_todo.data.model.TaskItem
import com.y_hori.minimum_todo.databinding.FragmentMainBinding
import com.y_hori.minimum_todo.utils.InjectorUtils

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels {
        InjectorUtils.provideTaskListViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main, container, false
        )

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = GroupAdapter<GroupieViewHolder>()

        binding.apply {
            taskList.adapter = adapter
            this.viewModel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
            this.fab.setOnClickListener { view ->
                view.findNavController()
                    .navigate(MainFragmentDirections.actionMainFragmentToCreateTaskFragment())
            }
        }
        subscribeUi(adapter)
    }

    private fun subscribeUi(adapter: GroupAdapter<GroupieViewHolder>) {

        viewModel.tasks.observe(viewLifecycleOwner) { tasks ->

            val taskItem = tasks.filter { it.isCompleted }.map {
                TaskItem(it)
            }
            adapter.update(taskItem)

        }
    }
}
