package com.bin.movie.data.model.remote.upload

import com.google.gson.annotations.SerializedName

data class UploadResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: String
)