package com.appwork.lufthasnaproject.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.appwork.lufthasnaproject.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.android.material.snackbar.Snackbar

fun Context.showToast(msg: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, msg, length).show()
}

fun View.showSnackBar(
    msg: String,
    length: Int = Snackbar.LENGTH_LONG
) {
    Snackbar.make(this, msg, length).show()
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.disable() {
    this.isEnabled = false
}

fun View.enable() {
    this.isEnabled = true
}

fun Context.setImage(
    view: ImageView,
    imagePath: String
) {
    Glide.with(this)
        .load(imagePath)
        .centerCrop()
        .skipMemoryCache(true)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .transform(CircleCrop())
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(view)
}