package com.sarfa.listdetailsexample.data.remote.item

import com.sarfa.domain.model.response.base.ResponseWrapper
import com.sarfa.domain.model.response.items.ItemListResponse
import com.sarfa.listdetailsexample.data.remote.base.ApiService
import com.sarfa.listdetailsexample.data.remote.base.SafeApiCall
import javax.inject.Inject

class ItemRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val safeApiCall: SafeApiCall
) {
    suspend fun getItems(): ResponseWrapper<ItemListResponse> = safeApiCall {
        apiService.getItems()
    }
}