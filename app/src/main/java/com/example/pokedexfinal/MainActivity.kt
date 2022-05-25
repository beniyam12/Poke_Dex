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
import com.example.pokedexfinal.DataClasses.Sprites
import com.example.pokedexfinal.DataClasses.myDataItem
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
        var input = Integer.parseInt(edittext.text.toString())
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
                val responseBody = response.body()!!

                val image = findViewById<ImageView>(R.id.imageView)

                    Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(image)


                val text_name = findViewById<TextView>(R.id.text_name)
                text_name.text = responseBody.name


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

        val retrofitData1 = retrofitBuilder.getgender("$input")
        retrofitData1.enqueue(object : Callback<myDataItem?> {
            override fun onResponse(call: Call<myDataItem?>, response: Response<myDataItem?>) {
                val responseBody = response.body()!!
                val text_gender = findViewById<TextView>(R.id.text_gender)
                text_gender.text = responseBody.name

            }

            override fun onFailure(call: Call<myDataItem?>, t: Throwable) {
                Log.d("MainActivity", "onFailure " + t.message)
            }
        })

        val retrofitData2 = retrofitBuilder.get_egg_group("$input")
        retrofitData2.enqueue(object : Callback<myDataItem?> {
            override fun onResponse(call: Call<myDataItem?>, response: Response<myDataItem?>) {
                val responseBody = response.body()!!
                val text_egg_group = findViewById<TextView>(R.id.text_egg_group)
                text_egg_group.text = responseBody.name
            }

            override fun onFailure(call: Call<myDataItem?>, t: Throwable) {
                Log.d("MainActivity", "onFailure " + t.message)
            }
        })
        val retrofitSprite = retrofitBuilder.getSpriteData("$input")
        retrofitSprite.enqueue(object : Callback<Sprites?> {
            override fun onResponse(call: Call<Sprites?>, response: Response<Sprites?>) {
                //val imageview = findViewById<ImageView>(R.id.imageView)
              //  val data = response.body()?.front_default
               // Picasso.get().load(data).into(imageView)

                val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$input.png"



                val imageView = findViewById<ImageView>(R.id.imageView)
                Picasso.get().load(url).into(imageView)
            }

            override fun onFailure(call: Call<Sprites?>, t: Throwable) {
                    val text = findViewById<TextView>(R.id.textView)
                    text.text = "fail"
            }
        })

    }

}