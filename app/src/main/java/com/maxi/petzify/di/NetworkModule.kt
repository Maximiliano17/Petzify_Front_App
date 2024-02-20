package com.maxi.petzify.di

import com.maxi.petzify.BuildConfig.BASE_URL
import com.maxi.petzify.data.repository.ProfileRepositoryImplementation
import com.maxi.petzify.data.core.interceptor.AuthInterceptor
import com.maxi.petzify.data.core.interceptor.TokenManager
import com.maxi.petzify.data.network.GetUserApiService
import com.maxi.petzify.data.network.PostApiService
import com.maxi.petzify.data.repository.LocalRepositoryImplementation
import com.maxi.petzify.data.repository.PostRepositoryImplementation
import com.maxi.petzify.domain.LocalRepository
import com.maxi.petzify.domain.PostsRepository
import com.maxi.petzify.domain.ProfileRepository
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

    //**********************************PROVEER RETROFIT******************************************
    //Proveer retrofit
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    //**********************************PROVEER INTERCEPTORES******************************************
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


    //**********************************PROVEER APISERVICES******************************************
    //Proveer un apiService especifico
    @Provides
    fun provideUserApiService(retrofit: Retrofit): GetUserApiService {
        return retrofit.create(GetUserApiService::class.java)
    }
    @Provides
    fun providePostApiService(retrofit: Retrofit): PostApiService {
        return retrofit.create(PostApiService::class.java)
    }

    //**********************************PROVEER REPOSITORIOS******************************************
    //Proveer un repositorio especifico
    @Provides
    fun provideRepository(apiService: GetUserApiService):ProfileRepository{
        return ProfileRepositoryImplementation(apiService)
    }

    @Provides
    fun providePostRepository(apiService: PostApiService):PostsRepository{
        return PostRepositoryImplementation(apiService)
    }

    @Provides
    fun provideLocalRepository():LocalRepository{
        return LocalRepositoryImplementation(TokenManager())
    }
}