package com.y_hori.minimum_todo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.y_hori.minimum_todo.data.TaskRepository
import com.y_hori.minimum_todo.data.model.Task
import kotlinx.coroutines.launch

class MainViewModel(private val repository: TaskRepository) : ViewModel() {

    private val _tasks = MutableLiveData<MutableList<Task>>()
    val tasks: LiveData<MutableList<Task>>
        get() = _tasks

    init {
        fetchTasks()
    }

    private fun fetchTasks() {
        viewModelScope.launch {
            _tasks.postValue(repository.getTasks())
        }
    }
}
