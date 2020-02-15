package com.y_hori.minimum_todo.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import java.util.*

@JsonClass(generateAdapter = true)
@Parcelize
class Task(
    @Transient
    var id: String = "",
    var title: String = "",
    var description: String = "",
    val timetamp: Long = Date().time,
    @Json(name = "is_completed")
    var isCompleted: Boolean = false
) : Parcelable {

    override fun toString(): String = title
}
