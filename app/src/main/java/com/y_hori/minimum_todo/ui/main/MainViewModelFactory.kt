package com.y_hori.minimum_todo.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.y_hori.minimum_todo.data.TaskRepository

@Suppress("UNCHECKED_CAST")
class PlantListViewModelFactory(
    private val repository: TaskRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) = MainViewModel(repository) as T
}
