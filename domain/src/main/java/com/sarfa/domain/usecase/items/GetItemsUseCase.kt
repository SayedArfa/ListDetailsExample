package com.sarfa.domain.usecase.items


import com.sarfa.domain.model.response.base.ResponseWrapper
import com.sarfa.domain.model.response.items.ItemListResponse
import com.sarfa.domain.repo.items.ItemRepo
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(private val itemRepo: ItemRepo) {
    suspend operator fun invoke(): ResponseWrapper<ItemListResponse> {
        return itemRepo.getItems()
    }
}