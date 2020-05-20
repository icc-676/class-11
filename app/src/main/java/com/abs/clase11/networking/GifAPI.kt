package com.abs.clase11.networking

import com.abs.clase11.model.GIFResponse
import retrofit2.Call
import retrofit2.http.GET

interface GifAPI {

    @GET("gifs/random")
    fun getRandomGif(): Call<GIFResponse>
}