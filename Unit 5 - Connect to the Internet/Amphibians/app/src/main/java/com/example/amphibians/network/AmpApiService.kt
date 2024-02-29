package com.example.amphibians.network

import com.example.amphibians.model.AmphibianCard
import retrofit2.http.GET

interface AmpApiService {
    @GET("amphibians")
    suspend fun getCards(): List<AmphibianCard>
}