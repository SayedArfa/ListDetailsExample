package com.sarfa.domain.repo.items

import com.sarfa.domain.model.response.base.ResponseWrapper
import com.sarfa.domain.model.response.items.ItemListResponse

interface ItemRepo {
    suspend fun getItems(): ResponseWrapper<ItemListResponse>
}