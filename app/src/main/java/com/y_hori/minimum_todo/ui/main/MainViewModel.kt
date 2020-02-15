package com.y_hori.minimum_todo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.y_hori.minimum_todo.data.TaskRepository
import com.y_hori.minimum_todo.data.model.Task
import com.y_hori.minimum_todo.data.model.User
import kotlinx.coroutines.launch

class MainViewModel(private val repository: TaskRepository) : ViewModel() {

    private val _tasks = MutableLiveData<MutableList<Task>>()
    val tasks: LiveData<MutableList<Task>>
        get() = _tasks

    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    private val _description = MutableLiveData<String>("")
    val description: LiveData<String>
        get() = _description

    private val _liveEventShowErrorDialog = MutableLiveData<Boolean>(false)
    val liveEventShowErrorDialog: LiveData<Boolean>
        get() = _liveEventShowErrorDialog

    private val _liveEventCreateTaskSuccess = MutableLiveData<Boolean>(false)
    val liveEventCreateTaskSuccess: LiveData<Boolean>
        get() = _liveEventCreateTaskSuccess


    private var user: User = User()

    fun fetchTasks() {
        viewModelScope.launch {
            repository.getTasks(user)?.let { tasks ->
                _tasks.postValue(tasks)
            }
        }
    }

    fun createTask() {
        if (title.value.isNullOrEmpty()) {
            _liveEventShowErrorDialog.value = true
            _liveEventShowErrorDialog.value = false
            return
        }
        viewModelScope.launch {
            repository.createTask(
                Task(title = title.value!!, description = description.value!!),
                user
            )?.let { tasks ->
                _tasks.postValue(tasks)
                _liveEventCreateTaskSuccess.value = true
                _liveEventCreateTaskSuccess.value = false
            }
        }
    }

    fun updateTitle(title: String) {
        _title.value = title
    }

    fun updateDescription(description: String) {
        _description.value = description
    }

    fun saveUser(user: User) {
        this.user = user
    }

    fun completeTask(task: Task) {
        viewModelScope.launch {
            repository.completeTask(task, user)?.let { tasks ->
                _tasks.postValue(tasks)
                _liveEventShowErrorDialog.value = true
                _liveEventShowErrorDialog.value = false
            }
        }
    }
}
