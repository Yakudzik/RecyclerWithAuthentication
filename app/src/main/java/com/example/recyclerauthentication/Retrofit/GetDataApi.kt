package com.example.recyclerauthentication.Retrofit

import com.example.recyclerauthentication.JsModel.JsonModelTwin
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface GetDataApi {

    @GET("api/v1/posts")
    fun searchMainData(): Call<JsonModelTwin>
    companion object {

        fun invoke(): GetDataApi {
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
                .create(GetDataApi::class.java)
        }
    }
}