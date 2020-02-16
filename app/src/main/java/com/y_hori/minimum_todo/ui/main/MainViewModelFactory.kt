package com.y_hori.minimum_todo.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.y_hori.minimum_todo.data.repository.TaskRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val repository: TaskRepository, private val application: Application
) : ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MainViewModel(repository,application) as T
}
