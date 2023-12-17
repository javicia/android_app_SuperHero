package com.example.superhero.response

import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse(@SerializedName("response") val response:String) {
}