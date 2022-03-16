package com.bin.movie.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bin.movie.data.model.Example

@Database(entities = [Example::class], version = 1)
abstract class BinDatabase : RoomDatabase() {

    abstract fun example(): ExampleDao

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