package com.sarfa.listdetailsexample.data.repo

import com.sarfa.domain.model.response.base.ResponseWrapper
import com.sarfa.domain.model.response.items.ItemListResponse
import com.sarfa.domain.repo.items.ItemRepo
import com.sarfa.listdetailsexample.data.remote.item.ItemRemoteDataSource
import javax.inject.Inject

class ItemRepoImp @Inject constructor(private val itemRemoteDataSource: ItemRemoteDataSource) :
    ItemRepo {
    override suspend fun getItems(): ResponseWrapper<ItemListResponse> {
        return itemRemoteDataSource.getItems()
    }
}