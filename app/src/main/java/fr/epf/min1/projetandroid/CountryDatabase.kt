package fr.epf.min1.projetandroid

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Country::class], version = 1)
@TypeConverters(Converters::class)
abstract class CountryDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao

    companion object {
        @Volatile private var instance: CountryDatabase? = null

        fun getDatabase(context: Context): CountryDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    CountryDatabase::class.java,
                    "country_database"
                ).build().also { instance = it }
            }
    }
}

