package com.example.hobbyapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.Date

@Entity
data class News (
    @ColumnInfo(name = "title")
    var title:String,
    @ColumnInfo(name = "imgUrl")
    var imgUrl:String,
    @ColumnInfo(name = "preview")
    var preview:String?,
    @ColumnInfo(name = "content")
    var content:String?,
    @ColumnInfo(name = "author")
    var author:String,
    @ColumnInfo(name = "createdAt")
    var createdAt:Date,
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}

@Entity
data class User (
    @ColumnInfo(name = "username")
    var username:String,
    @ColumnInfo(name = "password")
    var password:String?,
    @ColumnInfo(name = "firstname")
    var firstname:String,
    @ColumnInfo(name = "lastname")
    var lastname:String,
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}