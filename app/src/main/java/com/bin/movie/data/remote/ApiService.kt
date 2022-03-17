package com.bin.movie.data.remote

import com.bin.movie.data.model.remote.popular.PopularResponse
import com.bin.movie.data.model.remote.upcoming.UpcomingResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getMoviePopular(
        @Query("api_key") apiKey: String
    ) : ApiResponse<PopularResponse>

    @GET("movie/upcoming")
    fun getMovieUpcoming(
        @Query("api_key") apiKey: String
    ) : ApiResponse<UpcomingResponse>

}