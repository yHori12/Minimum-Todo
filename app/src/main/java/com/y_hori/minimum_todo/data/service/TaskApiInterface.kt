package com.y_hori.minimum_todo.data.service

import com.y_hori.minimum_todo.data.model.Task
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface TaskApiInterface {
    @GET("{user}/tasks.json")
    suspend fun fetchTasks(
        @Path("user") uid: String,
        @Query("auth") token:String
        ): Response<MutableList<Task>>
}
