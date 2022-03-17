package com.bin.movie.data.model.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "examples")
@Parcelize
data class Example(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo var name: String
) : Parcelable