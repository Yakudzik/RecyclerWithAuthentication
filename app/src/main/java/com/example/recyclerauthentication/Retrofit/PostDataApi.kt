package com.example.recyclerauthentication.Retrofit

import com.example.recyclerauthentication.JsModel.ResponseFromServ
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PostDataApi {
    @POST("api/v1/auth")
    @FormUrlEncoded

    fun registrationPost(
        @Field("phone") phoneNumber: String,
        @Field("password") password: String
    ): Call<ResponseFromServ>

    companion object {
        fun invoke(): PostDataApi {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl("http://dev-exam.l-tech.ru/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PostDataApi::class.java)
        }
    }
}