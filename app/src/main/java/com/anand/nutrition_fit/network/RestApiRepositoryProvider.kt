package com.anand.nutrition_fit.network

object RestApiRepositoryProvider {
    fun provideRestApiRepository(): RestApiRepository {
        return RestApiRepository(RestApiService.create())
    }
}