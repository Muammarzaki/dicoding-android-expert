package com.dicoding.core.data

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
