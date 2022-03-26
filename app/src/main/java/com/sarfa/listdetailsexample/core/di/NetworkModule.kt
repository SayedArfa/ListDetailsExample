package com.sarfa.listdetailsexample.core.di

import android.content.Context
import com.sarfa.listdetailsexample.data.remote.base.ApiService
import com.sarfa.listdetailsexample.data.remote.base.ApiService.Companion.BASE_URL
import com.sarfa.listdetailsexample.data.remote.util.BaseNetworkHelper
import com.sarfa.listdetailsexample.data.remote.util.NetworkHelper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .retryOnConnectionFailure(true)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideNetworkHelper(context: Context): BaseNetworkHelper {
        return NetworkHelper(context)
    }
}