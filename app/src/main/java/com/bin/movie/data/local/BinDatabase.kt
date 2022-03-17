package com.bin.movie.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bin.movie.data.model.local.MovieEntity
import com.bin.movie.data.model.local.PopularEntity
import com.bin.movie.data.model.local.UpComingEntity

@Database(
    entities = [
        MovieEntity::class,
        PopularEntity::class,
        UpComingEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BinDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun popularDao(): PopularDao
    abstract fun upComingDao(): UpComingDao

    companion object {
        private var database: BinDatabase? = null

        fun instance(context: Context): BinDatabase {
            if (database == null) {
                synchronized(BinDatabase::class) {
                    database = Room.databaseBuilder(
                        context.applicationContext,
                        BinDatabase::class.java,
                        "bin_database.db"
                    ).build()
                }
            }
            return database!!
        }

        fun destroyInstance() {
            database = null
        }
    }

}