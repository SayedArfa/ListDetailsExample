package com.sarfa.listdetailsexample.data.remote.base

import com.sarfa.domain.model.response.items.ItemListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    companion object {
        const val BASE_URL = "https://ey3f2y0nre.execute-api.us-east-1.amazonaws.com/default/"
    }

    @GET("dynamodb-writer")
    suspend fun getItems(): Response<ItemListResponse>
}