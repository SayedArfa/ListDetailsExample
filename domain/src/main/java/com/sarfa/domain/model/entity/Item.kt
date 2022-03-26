package com.sarfa.domain.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Item(
    val created_at: String,
    val image_ids: List<String>,
    val image_urls: List<String>,
    val image_urls_thumbnails: List<String>,
    val name: String,
    val price: String,
    val uid: String
) : Parcelable, Serializable