package com.y_hori.minimum_todo.ui.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.y_hori.minimum_todo.data.TaskRepository
import kotlinx.coroutines.launch

class CreateTaskViewModel(private val repository: TaskRepository) : ViewModel(){

    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    private val _description = MutableLiveData<String>()
    val description: LiveData<String>
        get() = _description


    fun createTask(){
        viewModelScope.launch {
            repository.postTask()
        }
    }

    fun updateTitle(title: String) {
        _title.value = title
    }

    fun updateDescription(description: String) {
        _description.value = description
    }
}
