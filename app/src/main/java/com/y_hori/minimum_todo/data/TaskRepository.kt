package com.y_hori.minimum_todo.data

import android.util.Log
import com.y_hori.minimum_todo.data.model.Task
import com.y_hori.minimum_todo.data.model.User
import com.y_hori.minimum_todo.data.service.*
import retrofit2.Response
import java.io.IOException

class TaskRepository(private val apiInterface: TaskApiInterface) : BaseRepository() {

    companion object {
        private var instance: TaskRepository? = null
        fun getInstance() =
            instance
                ?: synchronized(this) {
                instance
                    ?: TaskRepository(
                        TaskApiService.taskApi
                    ).also { instance = it }
            }
    }

    suspend fun getTasks(user: User): MutableList<Task>? {
        val result = apiOutput(
            call = {
                apiInterface.fetchTasks(
                    user.uid,
                    mapOf(Pair("Authorization", "Bearer ${user.token}"))
                )
            },
            error = "calling fetchTasks failed"
        )

        return when (result) {
            is NetworkResult.Success -> {
                result.output.documents?.map {
                    Task(
                        id = it.name.toDocumentId() ?: "",
                        title = it.fields.title.stringValue,
                        description = it.fields.description.stringValue,
                        isCompleted = it.fields.isCompleted.booleanValue
                    )
                }?.toMutableList()
            }
            is NetworkResult.Error -> {
                Log.d("Error", "${result.exception}")
                null
            }
        }
    }


    suspend fun createTask(task: Task, user: User): MutableList<Task>? {
        val result = apiOutput(
            call = {
                apiInterface.postTask(
                    user.uid,
                    mapOf(Pair("Authorization", "Bearer ${user.token}")),
                    Doument(
                        fields = Fields(
                            title = Title(
                                task.title
                            ),
                            description = Description(
                                task.description
                            ),
                            isCompleted = IsCompleted(
                                task.isCompleted
                            ),
                            dueDate = DueDate(
                                task.timetamp
                            )
                        )
                    )
                )
            },
            error = "calling putTask failed"
        )
        return when (result) {
            is NetworkResult.Success ->
                getTasks(user)
            is NetworkResult.Error -> {
                Log.d("Error", "${result.exception}")
                null
            }
        }
    }

    }

fun String?.toDocumentId(): String? {
    return this?.replace(".*/".toRegex(), "")
}


open class BaseRepository {
    suspend fun <T : Any> apiOutput(
        call: suspend () -> Response<T>,
        error: String
    ): NetworkResult<T> {
        val response = call.invoke()
        return if (response.isSuccessful) {
            if (response.body() != null) {
                NetworkResult.Success(
                    response.body()!!
                )
            } else {
                NetworkResult.Error(
                    IOException(error)
                )
            }
        } else {
            NetworkResult.Error(
                IOException(error)
            )
        }
    }
}

sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val output: T) : NetworkResult<T>()
    data class Error(val exception: Exception) : NetworkResult<Nothing>()
}
