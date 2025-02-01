package com.dicoding.core.di

import android.content.Context
import androidx.room.Room
import com.dicoding.core.BuildConfig
import com.dicoding.core.data.ArtWorkDao
import com.dicoding.core.data.Database
import com.dicoding.core.data.IAICEndpoint
import com.dicoding.core.data.RemoteKeysDao
import com.dicoding.core.domain.ArtsUseCase
import com.dicoding.core.domain.IAICRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    private fun provideCertificatePinning(): CertificatePinner {
        @Suppress("SpellCheckingInspection")
        return CertificatePinner.Builder()
            .add("artic.edu", "sha256/30Da9/6LeLRQmIEAuVQfsVp2TRAJNrMH4hzW/vwC4Js=")
            .build()
    }

    private fun provideClientConfig(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.BODY
            })
            .certificatePinner(provideCertificatePinning())
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
    fun provideDatabase(@ApplicationContext applicationContext: Context): Database {
        val passPhase = SQLiteDatabase.getBytes("arts".toCharArray())
        return Room.databaseBuilder(
            applicationContext,
            Database::class.java,
            "ColArt.db"
        ).openHelperFactory(
            SupportFactory(passPhase)
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(database: Database): ArtWorkDao = database.artWorkDao()

    @Provides
    @Singleton
    fun provideRemoteKeysDao(database: Database): RemoteKeysDao = database.remoteKeysDao()

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext applicationContext: Context): Context =
        applicationContext

    @Provides
    @Singleton
    fun provideArtsUseCase(repo: IAICRepository): ArtsUseCase {
        return ArtsUseCase(repo)
    }
}

