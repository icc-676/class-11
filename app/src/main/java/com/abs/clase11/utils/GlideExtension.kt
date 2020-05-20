package com.abs.clase11.utils

import android.widget.ImageView
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class GlideAppModule: AppGlideModule()

fun ImageView.loadGif(uri: String?) {
    GlideApp.with(this.context)
        .asGif()
        .centerInside()
        .load(uri)
        .into(this)
}

