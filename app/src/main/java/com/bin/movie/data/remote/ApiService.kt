package com.bin.movie.data.remote

import com.bin.movie.data.model.remote.popular.PopularResponse
import com.bin.movie.data.model.remote.search.SearchResponse
import com.bin.movie.data.model.remote.upcoming.UpcomingResponse
import com.bin.movie.data.model.remote.upload.UploadResponse
import com.skydoves.sandwich.ApiResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {

    @Multipart
    @POST
    suspend fun uploadFile(
        @Url url: String,
        @Part file: MultipartBody.Part,
        @Part("token") token: RequestBody
    ): ApiResponse<UploadResponse>

    @GET("movie/popular")
    suspend fun getMoviePopular(
        @Query("api_key") apiKey: String
    ): ApiResponse<PopularResponse>

    @GET("movie/upcoming")
    suspend fun getMovieUpcoming(
        @Query("api_key") apiKey: String
    ): ApiResponse<UpcomingResponse>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): ApiResponse<SearchResponse>
}