package com.mobile.dicoding

import androidx.databinding.BindingAdapter
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter("app:srcCompat")
fun setImageSrc(view: ShapeableImageView, resource: Int) {
    view.setImageResource(resource)
}