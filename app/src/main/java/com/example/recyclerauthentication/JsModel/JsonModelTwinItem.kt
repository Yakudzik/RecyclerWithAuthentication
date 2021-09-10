package com.example.recyclerauthentication.JsModel


import com.google.gson.annotations.SerializedName

data class JsonModelTwinItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("sort")
    val sort: Int,
    @SerializedName("date")
    val date: String
)
data class PhoneMask(
    @SerializedName("phoneMask")
    val mask: String
)
data class ResponseFromServ(
    @SerializedName("success")
    val success: Boolean
)