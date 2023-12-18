package com.example.superhero.detail

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.superhero.api.ApiService
import com.example.superhero.databinding.ActivityDetailSuperHeroBinding
import com.example.superhero.response.PowerStatsResponse
import com.example.superhero.response.SuperHeroDetailResponse
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperHeroActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailSuperHeroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperheroInformation(id)
    }

    private fun getSuperheroInformation(id: String) {
        //Hilo secundario IO
        CoroutineScope(Dispatchers.IO).launch {
            var superHeroDetails =
                getRetrofit().create(ApiService::class.java).getSuperHeroDetail(id)
            if (superHeroDetails.body() != null) {
                runOnUiThread { createUI(superHeroDetails.body()!!) }

            }

        }
    }

    private fun createUI(superhero: SuperHeroDetailResponse) {
        Picasso.get().load(superhero.image.url).into(binding.ivSuperHero)
        binding.tvSuperheroName.text = superhero.name
        prepareStats(superhero.powerstats)
        binding.tvSuperheroRealName.text=superhero.biography.fullName
        binding.tvPublisher.text=superhero.biography.publisher
    }

    private fun prepareStats(powerstats: PowerStatsResponse) {
        updateHeight(binding.viewCombat, powerstats.combat)
        updateHeight(binding.viewIntelligence, powerstats.intelligence)
        updateHeight(binding.viewStrength, powerstats.strength)
        updateHeight(binding.viewSpeed, powerstats.speed)
        updateHeight(binding.viewDurability, powerstats.durability)
        updateHeight(binding.viewPower, powerstats.power)
    }

    private fun updateHeight(view: View, stat: String) {
        val parans = view.layoutParams
        parans.height = pxUp(stat.toFloat())
        view.layoutParams = parans
    }

    private fun pxUp(px: Float): Int {
        //transformar dp a pixeles
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}