package com.example.pokedexfinal

import com.example.pokedexfinal.DataClasses.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("pokemon/{id}")
    fun getdata(@Path("id")id: String): Call<myDataItem>

    @GET("pokemon/{id}")
    fun getform(@Path("id")id: String): Call<Form>









}