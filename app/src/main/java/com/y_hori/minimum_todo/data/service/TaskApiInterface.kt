package com.y_hori.minimum_todo.data.service

import retrofit2.Response
import retrofit2.http.*

interface TaskApiInterface {
    @GET("(default)/documents/{user}")
    suspend fun fetchTasks(
        @Path("user") uid: String,
        @HeaderMap token: Map<String, String>
    ): Response<FirebaseApiResponse>

    @POST("(default)/documents/{user}/")
    suspend fun postTask(
        @Path("user") uid: String,
        @HeaderMap token: Map<String, String>,
        @Body task: Document
    ): Response<FirebaseApiResponse>

    @PATCH("(default)/documents/{user}/{taskId}")
    suspend fun patchTask(
        @Path("user") uid: String,
        @Path("taskId") taskId: String,
        @HeaderMap token: Map<String, String>,
        @Body task: Document
    ): Response<Document>
}
