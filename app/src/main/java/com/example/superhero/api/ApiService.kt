package com.example.superhero.api

import com.example.superhero.response.SuperHeroDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

     @GET("/api/10221078657709597/search/{name}")
     suspend fun getSuperHero(@Path("name") superHeroName: String): Response<SuperHeroDataResponse>
}