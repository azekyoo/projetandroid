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
}
