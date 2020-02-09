package com.y_hori.minimum_todo.utils

import com.y_hori.minimum_todo.ui.main.PlantListViewModelFactory
import com.y_hori.minimum_todo.data.TaskRepository

object InjectorUtils {
    fun provideTaskListViewModelFactory(): PlantListViewModelFactory {
        val repository =
            getTaskRepository()
        return PlantListViewModelFactory(
            repository
        )
    }

    private fun getTaskRepository(): TaskRepository {
        return TaskRepository.getInstance()
    }
}
