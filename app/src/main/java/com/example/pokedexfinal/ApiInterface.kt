package com.example.pokedexfinal

import com.example.pokedexfinal.DataClasses.Sprites
import com.example.pokedexfinal.DataClasses.myDataItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("pokemon/{id}")
    fun getdata(@Path("id")id: String): Call<myDataItem>

    // @GET("pokemon/{id}")
   //  fun getSpriteData(@Path("id")id: String): Call<Sprites>
    @GET("{id}")
    fun getSpriteData(@Path("id")id: String): Call<Sprites>

    @GET("gender/{id}")
    fun getgender(@Path("id")id: String): Call<myDataItem>

    @GET("egg-group/{id}")
    fun get_egg_group(@Path("id")id: String): Call<myDataItem>





}