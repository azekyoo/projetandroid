package fr.epf.min1.projetandroid

import kotlinx.coroutines.CoroutineScope
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryRepository(private val coroutineScope: CoroutineScope) {
    private val api: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(ApiService::class.java)
    }

    suspend fun getAllCountries(): List<Country> {
        return api.getAllCountries()
    }

    suspend fun searchCountryByName(name: String): List<Country> {
        return api.searchCountryByName(name)
    }
}
