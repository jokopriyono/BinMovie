package com.bin.movie.data.model.local

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize


@Entity(
    tableName = "popular_movies",
    foreignKeys = [ForeignKey(
        entity = MovieEntity::class,
        parentColumns = ["id"],
        childColumns = ["movie_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
@Parcelize
data class PopularEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    @ColumnInfo(name = "movie_id", index = true)
    val movieId: Long
) : Parcelable