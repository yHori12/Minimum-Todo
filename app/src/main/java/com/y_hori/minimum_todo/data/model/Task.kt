package com.y_hori.minimum_todo.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Task(
    @Transient
    var id: String = "",
    var title: String = "",
    var description: String = "",
    val dueDate: Long = 0L,
    @Json(name = "is_completed")
    var isCompleted: Boolean = false
) : Parcelable {

    override fun toString(): String = title
}
