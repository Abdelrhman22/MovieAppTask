package com.task.movieapp.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.task.movieapp.R

@BindingAdapter("imageURL", "loader")
fun setImageFromURL(imageView: ImageView, iconUrl: String?, loader: LottieAnimationView) {
    iconUrl?.let {
        Glide.with(imageView.context)
            .load(it)
            .addListener(imageLoadingListener(loader))
            .error(R.drawable.ic_error)
            .into(imageView)
    }
}

private fun imageLoadingListener(pendingImage: LottieAnimationView): RequestListener<Drawable?>? {
    return object : RequestListener<Drawable?> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: com.bumptech.glide.request.target.Target<Drawable?>?,
            isFirstResource: Boolean
        ): Boolean {
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: com.bumptech.glide.request.target.Target<Drawable?>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            pendingImage.pauseAnimation()
            pendingImage.visibility = View.GONE
            return false
        }
    }
}


@BindingAdapter("doubleValue")
fun setDoubleValue(textView: TextView, value: Double?) {
    value?.let {
        textView.text = "%.2f".format(value)
    }
}


@BindingAdapter("visibleGone")
fun bindViewsVisibility(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("progressValue")
fun setProgressValue(progressBar: ProgressBar, progress: Double?) {
    progress?.let {
        progressBar.progress = (it * 10).toInt() // Convert double to integer percentage
    }
}

@BindingAdapter("iconURL")
fun setIconFromURL(imageView: ImageView, iconUrl: String?) {
    iconUrl?.let {
        Glide.with(imageView.context)
            .load(it)
            .error(R.drawable.ic_error)
            .into(imageView)
    }
}