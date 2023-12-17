package com.example.superhero.api

import android.adservices.adid.AdId
import com.example.superhero.response.SuperHeroDataResponse
import com.example.superhero.response.SuperHeroDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

     @GET("/api/10221078657709597/search/{name}")
     suspend fun getSuperHero(@Path("name") superHeroName: String): Response<SuperHeroDataResponse>

     @GET("/api/10221078657709597/{id}")
     suspend fun getSuperHeroDetail (@Path("id") superHeroId: String): Response<SuperHeroDetailResponse>
}