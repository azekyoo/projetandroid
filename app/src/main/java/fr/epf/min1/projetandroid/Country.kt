package fr.epf.min1.projetandroid

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "favorite_countries")
@TypeConverters(Converters::class)
data class Country(
    @PrimaryKey val name: String,
    val capital: String,
    val flags: Flags,
    val population: Long,
    val area: Double,
    val currencies: List<Currency>,
    val region: String,
    val subregion: String,
    val languages: List<Language>
)
data class Flags(
    val svg: String,
    val png: String
)
data class Currency(
    val code: String,
    val name: String,
    val symbol: String
)

data class Language(
    val iso639_1: String,
    val iso639_2: String,
    val name: String,
    val nativeName: String
)
