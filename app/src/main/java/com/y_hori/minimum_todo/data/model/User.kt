package com.y_hori.minimum_todo.data.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class User(
    val token: String = "",
    var uid: String = ""
) : Parcelable {
    @IgnoredOnParcel
    var name: String? = ""
}

@JsonClass(generateAdapter = true)
data class UserItem(
    val first:String,
    val last:String
)
