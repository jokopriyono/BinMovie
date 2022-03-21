package com.bin.movie.utils

import com.bin.movie.data.model.local.MovieEntity

object MockUtils {

    fun mockMovieEntity() = MovieEntity(
        false,
        "/inifoto.jpeg",
        "1,2,3",
        1,
        "en",
        "Upin Ipin Yang Tertukar",
        "Ini merupakan dongeng yang tidak nyata",
        100.0,
        "/iniposter.png",
        "30 Februari 2012",
        "Upin Ipin Yang Tertukar",
        false,
        8.5,
        302
    )

    fun mockMoviesEntity() = listOf(mockMovieEntity())

}