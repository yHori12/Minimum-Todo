package com.y_hori.minimum_todo.utils

import com.y_hori.minimum_todo.data.repository.TaskRepository
import com.y_hori.minimum_todo.ui.main.MainViewModelFactory

object InjectorUtils {
    fun provideTaskListViewModelFactory(): MainViewModelFactory {
        val repository =
            getTaskRepository()
        return MainViewModelFactory(
            repository
        )
    }

    private fun getTaskRepository(): TaskRepository {
        return TaskRepository.getInstance()
    }
}
