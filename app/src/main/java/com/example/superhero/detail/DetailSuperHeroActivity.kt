package com.example.superhero.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import com.example.superhero.R
import com.example.superhero.api.ApiService
import com.example.superhero.response.SuperHeroDataResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailSuperHeroActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_ID ="extra_id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_super_hero)
        val id:String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperheroInformation(id)
    }

    private fun getSuperheroInformation(id: String) {
        //Hilo secundario IO
        CoroutineScope(Dispatchers.IO).launch {
            getRetrofit()create(ApiService::class.java).getSuperHero(query)
            val myResponse: Response<SuperHeroDataResponse> =
                retrofit.create(ApiService::class.java).getSuperHero(query)
            if (myResponse.isSuccessful) {
                Log.i("Javicia", "funciona: ")
                var response: SuperHeroDataResponse? = myResponse.body()
                if(response != null){
                    Log.i("javicia", response.toString())
                    runOnUiThread{
                        adapter.updateList(response.superheroes)
                        binding.progressBar.isVisible = false
                    }
                }
            }else{
                Log.i("javicia", "no funciona")
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}