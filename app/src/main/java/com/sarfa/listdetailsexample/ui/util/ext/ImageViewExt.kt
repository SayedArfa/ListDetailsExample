package com.sarfa.listdetailsexample.ui.util.ext

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.sarfa.listdetailsexample.R

fun ImageView.loadFromUrl(
    imageUrl: String?,
    errorImage: Int = R.drawable.ic_empty_img,
    onImageLoadResult: (Boolean) -> Unit = {}
) {
    val requestOption = RequestOptions()
        .placeholder(errorImage).centerInside()
        .error(errorImage)
    imageUrl?.let {
        val imgUri = imageUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(context)
            .load(imgUri)
            .apply(requestOption).transition(DrawableTransitionOptions.withCrossFade())
            .addListener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    isFirstResource: Boolean
                ): Boolean {
                    onImageLoadResult.invoke(false)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    onImageLoadResult.invoke(true)
                    return false
                }
            })
            .into(this)

    }


}