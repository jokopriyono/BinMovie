package com.bin.movie.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.bin.movie.data.local.BinDatabase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21]) // test disimulasikan di android Lollipop
abstract class BinDatabaseTest {
    lateinit var database: BinDatabase

    @Before
    fun initDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            BinDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

}