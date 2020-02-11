package com.y_hori.minimum_todo.data.service

import com.y_hori.minimum_todo.data.model.Task
import retrofit2.Response
import retrofit2.http.GET

interface TaskApiInterface {
    @GET()
    suspend fun fetchTasks(uid: String):Response<MutableList<Task>>
}
