package com.example.hobbyapp.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class News (
    var id:Int,
    var title:String,
    @SerializedName("image")
    var imgUrl:String,
    var preview:String?,
    var content:String?,
    var author:String,
    @SerializedName("created_at")
    var createdAt:Date
)

data class User (
    var id:Int,
    var username:String,
    var password:String?,
    var firstname:String,
    var lastname:String,
)