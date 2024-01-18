package com.maxi.petzify.data.network

import com.maxi.petzify.BuildConfig.BASE_URL
import com.maxi.petzify.data.UserRepositoryImplementation
import com.maxi.petzify.data.core.interceptor.AuthInterceptor
import com.maxi.petzify.domain.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    //Proveer retrofit
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //Proveer interceptores
    @Provides
    @Singleton
    fun provideokHttpClient(authInterceptor: AuthInterceptor):OkHttpClient{
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    //Proveer un apiService especifico
    @Provides
    fun provideUserApiService(retrofit: Retrofit): GetUserApiService {
        return retrofit.create(GetUserApiService::class.java)
    }

    //Proveer un repositorio especifico
    @Provides
    fun provideRepository(apiService: GetUserApiService):UserRepository{
        return UserRepositoryImplementation(apiService)
    }
}