package com.sarfa.domain.model.response.items

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sarfa.domain.model.entity.Item
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class ItemListResponse(@SerializedName("results") val items: List<Item>) : Parcelable,
    Serializable
