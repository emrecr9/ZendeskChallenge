package com.example.zendesk.Objects

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//the retrofict object that contains funtion
object Retro
{
    fun provideRetrofit(baseUrl:String): Retrofit =
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(
                            OkHttpClient.Builder().callTimeout(5, TimeUnit.SECONDS)
                                    .addInterceptor {
                                it.proceed(
                                        it.request().newBuilder()
                                                .addHeader("Authorization", "Basic " + "HERE GOES THE {email_address}:{password} OF THE AGENT IN BASEcode64")
                                                .build()
                                )
                            }.addInterceptor(HttpLoggingInterceptor()
                                            .also { it.level = HttpLoggingInterceptor.Level.NONE })
                                    .build()
                    )
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
}