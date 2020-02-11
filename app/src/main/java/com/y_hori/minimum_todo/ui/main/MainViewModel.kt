package com.y_hori.minimum_todo.ui.main

import androidx.lifecycle.*
import com.y_hori.minimum_todo.data.TaskRepository
import com.y_hori.minimum_todo.data.model.Task
import kotlinx.coroutines.launch

class MainViewModel(private val repository: TaskRepository) : ViewModel() {

    private val _tasks = MutableLiveData<MutableList<Task>>()
    val tasks: LiveData<MutableList<Task>>
        get() = _tasks

    private fun fetchTasks(uid: String) {
        viewModelScope.launch {
            _tasks.postValue(repository.getTasks(uid))
        }
    }

    fun init(uid: String) {
        fetchTasks(uid)
    }
}
