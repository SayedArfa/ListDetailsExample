package com.sarfa.listdetailsexample.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarfa.domain.model.response.base.ResponseWrapper
import com.sarfa.domain.model.response.items.ItemListResponse
import com.sarfa.domain.usecase.items.GetItemsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ItemListViewModel @Inject constructor(private val itemsUseCase: GetItemsUseCase) :
    ViewModel() {
    private val _itemsLiveData = MutableLiveData<ResponseWrapper<ItemListResponse>>()
    val itemsLiveData: LiveData<ResponseWrapper<ItemListResponse>> = _itemsLiveData
    fun loadList() {
        viewModelScope.launch {
            _itemsLiveData.value = ResponseWrapper.Loading
            val response = itemsUseCase()
            _itemsLiveData.value = response
        }
    }
}