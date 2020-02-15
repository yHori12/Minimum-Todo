package com.y_hori.minimum_todo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.y_hori.minimum_todo.R
import com.y_hori.minimum_todo.data.model.User
import com.y_hori.minimum_todo.databinding.FragmentMainBinding
import com.y_hori.minimum_todo.ui.create.CreateTaskFragmentDirections
import com.y_hori.minimum_todo.ui.listitem.CompletedTaskItem
import com.y_hori.minimum_todo.ui.listitem.ExpandableHeaderItem
import com.y_hori.minimum_todo.ui.listitem.NewTaskItem
import com.y_hori.minimum_todo.ui.listitem.OnClickTaskItem
import com.y_hori.minimum_todo.ui.splash.SplashActivity
import com.y_hori.minimum_todo.utils.InjectorUtils

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val mainViewModel: MainViewModel by activityViewModels {
        InjectorUtils.provideTaskListViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user =
            activity?.intent?.getParcelableExtra<User>(SplashActivity.INTENT_KEY_USER) ?: return
        mainViewModel.saveUser(user)
        mainViewModel.fetchTasks()
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
            this.viewModel = mainViewModel
            this.lifecycleOwner = viewLifecycleOwner
            this.fab.setOnClickListener { view ->
                view.findNavController()
                    .navigate(MainFragmentDirections.actionMainFragmentToCreateTaskFragment())
            }
        }
        subscribeUi(adapter)
    }

    private fun subscribeUi(adapter: GroupAdapter<GroupieViewHolder>) {
        mainViewModel.tasks.observe(viewLifecycleOwner) { tasks ->
            val newTasks = tasks.filter { !it.isCompleted }.map {
                NewTaskItem(it, object : OnClickTaskItem {
                    override fun onClickComplete() {
                        mainViewModel.completeTask(it.apply {
                            it.isCompleted = true
                        })
                    }
                })
            }
            adapter.update(newTasks)

            val completedTasks = tasks.filter { it.isCompleted }.map { CompletedTaskItem(it) }
            if (!completedTasks.isNullOrEmpty()){
                val expandableGroup = ExpandableGroup(ExpandableHeaderItem(R.string.expanding_group))
                expandableGroup.addAll(completedTasks)
                adapter.add(expandableGroup)
            }
        }
    }
}
