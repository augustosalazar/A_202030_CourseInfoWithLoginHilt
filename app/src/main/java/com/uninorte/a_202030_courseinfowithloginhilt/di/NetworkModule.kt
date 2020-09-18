package com.uninorte.a_202030_courseinfowithloginhilt.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.uninorte.a_202030_courseinfowithlogin.service.api.course.CourseApi
import com.uninorte.a_202030_courseinfowithlogin.service.api.login.LoginApi
import com.uninorte.a_202030_courseinfowithlogin.service.api.login.LoginApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideLoginRetrofit(gson: Gson,okHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
            .baseUrl("https://movil-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()


    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient{
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .readTimeout(100, TimeUnit.SECONDS)
            .connectTimeout(100, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideLoginApiService(retrofit: Retrofit): LoginApi = retrofit.create(LoginApi::class.java)

    @Provides
    @Singleton
    fun provideCourseApiService(retrofit: Retrofit): CourseApi = retrofit.create(CourseApi::class.java)

}