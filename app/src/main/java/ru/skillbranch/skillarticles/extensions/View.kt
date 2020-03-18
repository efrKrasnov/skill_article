package ru.skillbranch.skillarticles.extensions

import android.view.View
import android.view.ViewGroup
import androidx.core.view.*

fun View.setMarginOptionally(
    left: Int = marginLeft,
    top: Int = marginTop,
    right: Int = marginRight,
    bottom: Int = marginBottom
) {
    if (layoutParams is ViewGroup.MarginLayoutParams)   {
        val lp = layoutParams as ViewGroup.MarginLayoutParams
        lp.setMargins(left, top, right, bottom)
        requestLayout()
    }
}