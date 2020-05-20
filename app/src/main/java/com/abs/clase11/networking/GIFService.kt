package com.abs.clase11.networking

import com.abs.clase11.utils.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


object GIFService {

    val interceptor = HttpLoggingInterceptor()
    private val client = OkHttpClient
        .Builder()
        .addInterceptor(interceptor)
        .addInterceptor(object: Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("api_key", "rdsQpyhByexQhk59OFNaF4ypLAXufKY1")
                    .build()
                return chain.proceed(newRequest)
            }
        })
        .build()


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}