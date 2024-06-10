package fr.epf.min1.projetandroid

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_countries")
data class Country(
    @PrimaryKey val name: String,
    val capital: String,
    val flags: Flags,
)
data class Flags(
    val svg: String,
    val png: String
)
