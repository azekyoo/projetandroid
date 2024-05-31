package fr.epf.min1.projetandroid

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v3.1/all")
    suspend fun getAllCountries(): List<Country>

    @GET("v3.1/name/{name}")
    suspend fun searchCountries(@Query("name") name: String): List<Country>
}
