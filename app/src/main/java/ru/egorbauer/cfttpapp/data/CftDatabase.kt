package ru.egorbauer.cfttpapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        DatabaseUser::class
    ]
)
abstract class CftDatabase: RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: CftDatabase? = null
        fun getDatabase(context: Context): CftDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CftDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
    abstract fun getUserDao():UserDao
}