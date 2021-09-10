package com.example.recyclerauthentication.Retrofit

import com.example.recyclerauthentication.JsModel.PhoneMask
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

interface GetMaskApi {
    @GET("api/v1/phone_masks")
    fun getPhoneMask():Call<PhoneMask>
    companion object{
        fun invoke(): GetMaskApi {
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
                .create(GetMaskApi::class.java)
        }
    }
}