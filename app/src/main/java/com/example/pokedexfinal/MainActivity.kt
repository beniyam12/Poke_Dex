package com.example.pokedexfinal

import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

import com.example.pokedexfinal.DataClasses.*
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

const val BASE_URL = "https://pokeapi.co/api/v2/"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener() {
            fetchdata()
        }

    }
    fun fetchdata() {
        val edittext = findViewById<EditText>(R.id.editTextTextPersonName)
        val input = (edittext.text.toString())
        val imageView = findViewById<ImageView>(R.id.imageView)
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)


        val retrofitData = retrofitBuilder.getdata("$input")


        retrofitData.enqueue(object : Callback<myDataItem?> {
            override fun onResponse(

                call: Call <myDataItem?>,
                response: Response<myDataItem?>
            ) {
                val body = response.body()
                var id_input = body?.id
                val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id_input.png"



                Picasso.get().load(url).resize(500,500).into(imageView)
                //imageView.load(url)

                val responseBody = response.body()!!


                val text_name = findViewById<TextView>(R.id.text_name)
                text_name.text = responseBody.name

                val text_id = findViewById<TextView>(R.id.text_id)
                text_id.text = responseBody.id.toString()
                val text_BaseStat = findViewById<TextView>(R.id.text_BaseStat)
                text_BaseStat.text = responseBody.base_experience.toString()


                val text_Height = findViewById<TextView>(R.id.text_height)
                text_Height.text = responseBody.height.toString()

                val text_Weight = findViewById<TextView>(R.id.text_weight)
                text_Weight.text = responseBody.weight.toString()





            }

            override fun onFailure(call: Call<myDataItem?>, t: Throwable) {
               Log.d("MainActivity", "onFailure " + t.message)

            }
        })









    }

}