package com.mcdenny.interswitchtechnicaltest.data.remote.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mcdenny.interswitchtechnicaltest.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideLogger(): HttpLoggingInterceptor.Logger {
        return HttpLoggingInterceptor.Logger { message -> Timber.i(message) }
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(
        logger: HttpLoggingInterceptor.Logger
    ): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(logger).apply {
            level = when (BuildConfig.BUILD_TYPE) {
                "debug" -> HttpLoggingInterceptor.Level.BODY
                else -> HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    internal fun provideOkhttpClient(
        cache: Cache,
        interceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            cache(cache)
            addInterceptor(interceptor)
        }
        return builder.build()
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    internal fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    internal fun provideApiRetrofit(
        client: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        val builder = Retrofit.Builder()
        builder.apply {
            baseUrl(BuildConfig.BASE_URL)
            client(client)
            addConverterFactory(converterFactory)
        }
        return builder.build()
    }
}