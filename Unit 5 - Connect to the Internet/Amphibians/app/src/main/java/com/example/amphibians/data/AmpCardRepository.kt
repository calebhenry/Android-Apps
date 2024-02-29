package com.example.amphibians.data

import com.example.amphibians.model.AmphibianCard
import com.example.amphibians.network.AmpApiService

interface AmpCardRepository {
    suspend fun getAmpCards(): List<AmphibianCard>
}

class NetworkAmpCardRepository(
    private val ampApiService: AmpApiService
) : AmpCardRepository {
    override suspend fun getAmpCards(): List<AmphibianCard> = ampApiService.getCards()
}