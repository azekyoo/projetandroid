package fr.epf.min1.projetandroid

import retrofit2.http.Query
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("countries")
    suspend fun getAllCountries(): List<Country>

    @GET("name/{name}")
    suspend fun searchCountryByName(@Path("name") name: String): List<Country>

    @GET("alpha/{code}")
    suspend fun getCountryByAlphaCode(@Path("code") code: String): Country

    @GET("borders/{name}")
    suspend fun getCountriesByBorders(@Path("name") name: String): List<Country>

    @GET("callingcode/{code}")
    suspend fun getCountryByCallingCode(@Path("code") code: String): Country

    @GET("capital/{capital}")
    suspend fun getCountryByCapital(@Path("capital") capital: String): Country

    @GET("lang/{language}")
    suspend fun getCountriesByLanguage(@Path("language") language: String): List<Country>

    @GET("region/{region}")
    suspend fun getCountriesByRegion(@Path("region") region: String): List<Country>

    @GET("subregion/{subregion}")
    suspend fun getCountriesBySubregion(@Path("subregion") subregion: String): List<Country>
}
