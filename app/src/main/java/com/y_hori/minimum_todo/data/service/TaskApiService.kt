package com.y_hori.minimum_todo.data.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object TaskApiService : BaseApiService() {
    val taskApi = getRetrofit().create(TaskApiInterface::class.java)
}

open class BaseApiService {
    companion object {
        private const val baseUrl = "https://firestore.googleapis.com/v1/projects/fir-cloudstorerealtimeexam/databases/"
    }

    private val client: OkHttpClient
        get() {
            return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }).build()
        }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(
                        KotlinJsonAdapterFactory()
                    ).build()
                )
            ).build()
    }
}
