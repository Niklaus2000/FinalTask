package com.example.myapplication.DI

import com.example.myapplication.Common.Constants
import com.example.myapplication.Data.Remote.NoteApiService
import com.example.myapplication.Data.Repository.NoteRepositoryImpl
import com.example.myapplication.Domain.Repository.NoteRepository
import com.example.myapplication.network.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthInterceptor(): AuthInterceptor {
        return AuthInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteApi(okHttpClient: OkHttpClient): NoteApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NoteApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNoteRepository(api: NoteApiService): NoteRepository {
        return NoteRepositoryImpl(api)
    }
}