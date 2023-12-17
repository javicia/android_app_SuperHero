package com.example.superhero.response

import com.google.gson.annotations.SerializedName

data class SuperHeroDetailResponse(@SerializedName("name") val name: String,
                                   @SerializedName("powerstats") val powerstats: String)
data class PowerStatsResponse(
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("combat") val combat: String,

    )
