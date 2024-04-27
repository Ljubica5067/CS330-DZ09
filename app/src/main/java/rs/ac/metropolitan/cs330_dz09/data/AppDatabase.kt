package rs.ac.metropolitan.cs330_dz09.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import rs.ac.metropolitan.cs330_dz09.data.entity.Recept
import rs.ac.metropolitan.cs330_dz09.data.entity.Sastojak
import rs.ac.metropolitan.cs330_dz09.data.entity.Torta

@Database(entities = [Recept::class, Sastojak::class, Torta::class], version = 1)
abstract class AppDatabase :RoomDatabase(){
    abstract fun appDao(): AppDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}