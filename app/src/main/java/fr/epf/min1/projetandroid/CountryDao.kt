package fr.epf.min1.projetandroid

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CountryDao {
    @Query("SELECT EXISTS (SELECT 1 FROM favorite_countries WHERE name = :countryName LIMIT 1)")
    suspend fun isFavorite(countryName: String): Boolean
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(country: Country)

    @Query("DELETE FROM favorite_countries WHERE name = :countryName")
    suspend fun delete(countryName: String)

    @Query("SELECT * FROM favorite_countries")
    suspend fun getAllFavorites(): List<Country>
}
