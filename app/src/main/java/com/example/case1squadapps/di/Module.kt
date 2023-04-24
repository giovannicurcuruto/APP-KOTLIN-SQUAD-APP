package com.example.case1squadapps.di

import com.example.case1squadapps.others.Constants
import com.example.case1squadapps.others.Constants.BASE_URL
import com.example.case1squadapps.others.api
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@dagger.Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient{
        val loggin = HttpLoggingInterceptor()
        loggin.level = HttpLoggingInterceptor.Level.BODY
        
        return OkHttpClient().newBuilder()
            .addInterceptor { chain ->
                val newUrl = chain.request()
                    .url.newBuilder().
                    build()

                val newRequest = chain.request()
                    .newBuilder()
                    .url(newUrl)
                    .addHeader("Authorization", "Bearer ${Constants.TOKKEN_KEY}")
                    .build()

                chain.proceed(newRequest)
            }.addInterceptor(loggin)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build()
    }

    @Singleton
    @Provides
    fun provideServiceApi(retrofit: Retrofit): api{
        return retrofit.create(api::class.java)
    }

}