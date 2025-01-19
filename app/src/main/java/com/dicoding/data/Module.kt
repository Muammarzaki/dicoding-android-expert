package com.dicoding.data

import android.content.Context
import androidx.room.Room
import com.dicoding.BuildConfig
import com.dicoding.domain.IAICRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {
    private fun provideClientConfig(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    private fun provideConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideApiClient(): IAICEndpoint {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.IAC_BASE_URL)
            .addConverterFactory(provideConverter())
            .client(provideClientConfig())
            .build()
        return retrofit.create(IAICEndpoint::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext applicationContext: Context): Database =
        Room.databaseBuilder(
            applicationContext,
            Database::class.java,
            applicationContext.packageName
        ).build()

    @Provides
    @Singleton
    fun provideDao(database: Database): ArtWorkDao = database.artWorkDao()

    @Provides
    @Singleton
    fun provideRemoteKeysDao(database: Database): RemoteKeysDao = database.remoteKeysDao()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBinding {
    @Binds
    abstract fun provideAICRepository(aicRepository: AICRepository): IAICRepository
}
