package com.example.apitest.retrofit

import com.example.apitest.classes.CardClass
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface{
    @GET("api/colors/new?format=json")
    suspend fun getColors(): Response<List<CardClass>>

}

object RetrofitClass {
    val api:ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.colourlovers.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }





}