package com.dicoding.data

import com.dicoding.domain.AICArtWorkResponse
import com.dicoding.domain.AICArtWorksResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IAICEndpoint {
    @GET("api/v1/artworks?")
    suspend fun getArtWorks(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 25
    ): AICArtWorksResponse

    @GET("api/v1/artworks/{id}")
    suspend fun getArtWork(@Path("id") id: String): AICArtWorkResponse
}
