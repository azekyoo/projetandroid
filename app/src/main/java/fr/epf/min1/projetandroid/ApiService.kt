package fr.epf.min1.projetandroid

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("countries")
    suspend fun getAllCountries(): List<Country>

    @GET("countries")
    suspend fun searchCountryByName(@Query("name") name: String): List<Country>
}
