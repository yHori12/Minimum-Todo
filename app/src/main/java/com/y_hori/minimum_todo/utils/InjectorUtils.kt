package com.y_hori.minimum_todo.utils

import com.y_hori.minimum_todo.ui.main.MainViewModelFactory
import com.y_hori.minimum_todo.data.TaskRepository
import com.y_hori.minimum_todo.ui.create.CreateTaskViewModel
import com.y_hori.minimum_todo.ui.create.CreateTaskViewModelFactory

object InjectorUtils {
    fun provideTaskListViewModelFactory(): MainViewModelFactory {
        val repository =
            getTaskRepository()
        return MainViewModelFactory(
            repository
        )
    }

    fun provideCreateTaskViewModelFactory(): CreateTaskViewModelFactory {
        val repository =
            getTaskRepository()
        return CreateTaskViewModelFactory(
            repository
        )
    }


    private fun getTaskRepository(): TaskRepository {
        return TaskRepository.getInstance()
    }
}
