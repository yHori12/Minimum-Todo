package com.y_hori.minimum_todo.data.service

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FirebaseApiResponse(
    val documents: List<Document>?
)

@JsonClass(generateAdapter = true)
data class Document(
    val createTime: String? = null,
    val fields: Fields,
    val name: String? = null,
    val updateTime: String? = null
)

@JsonClass(generateAdapter = true)
data class Fields(
    val title: Title,
    val description: Description,
    @Json(name = "is_completed")
    val isCompleted: IsCompleted,
    @Json(name = "due_date")
    val dueDate: DueDate
)

@JsonClass(generateAdapter = true)
data class Title(
    val stringValue: String
)

@JsonClass(generateAdapter = true)
data class Description(
    val stringValue: String
)

@JsonClass(generateAdapter = true)
data class IsCompleted(
    val booleanValue: Boolean
)

@JsonClass(generateAdapter = true)
data class DueDate(
    val integerValue: Long
)
