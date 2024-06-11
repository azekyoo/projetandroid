package fr.epf.min1.projetandroid

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromFlags(flags: Flags): String {
        return Gson().toJson(flags)
    }

    @TypeConverter
    fun toFlags(flagsString: String): Flags {
        val type = object : TypeToken<Flags>() {}.type
        return Gson().fromJson(flagsString, type)
    }
    @TypeConverter
    fun fromCurrency(currency: List<Currency>): String {
        return Gson().toJson(currency)
    }

    @TypeConverter
    fun toCurrency(currencyString: String): List<Currency> {
        val listType = object : TypeToken<List<Currency>>() {}.type
        return Gson().fromJson(currencyString, listType)
    }

    @TypeConverter
    fun fromLanguages(languages: List<Language>): String {
        return Gson().toJson(languages)
    }

    @TypeConverter
    fun toLanguages(languagesString: String): List<Language> {
        val listType = object : TypeToken<List<Language>>() {}.type
        return Gson().fromJson(languagesString, listType)
    }
    @TypeConverter
    fun fromLatLngList(value: List<Double>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toLatLngList(value: String): List<Double> {
        val listType = object : TypeToken<List<Double>>() {}.type
        return Gson().fromJson(value, listType)
    }
}
