package fr.iut.piscinenetptut.library.extension

import android.os.Build
import android.view.View
import android.view.ViewGroup

fun View.setMargin(marginStart: Int?, top: Int?, marginEnd: Int?, bottom: Int?) {
    try {
        val marginLayoutParams: ViewGroup.MarginLayoutParams =
            this.layoutParams as ViewGroup.MarginLayoutParams

        if (null != marginStart) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                marginLayoutParams.marginStart = marginStart
            } else {
                marginLayoutParams.leftMargin = marginStart
            }
        }

        if (null != top) {
            marginLayoutParams.topMargin = top
        }

        if (null != marginEnd) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                marginLayoutParams.marginEnd = marginEnd
            } else {
                marginLayoutParams.rightMargin = marginEnd
            }
        }

        if (null != bottom) {
            marginLayoutParams.bottomMargin = bottom
        }

        this.layoutParams = marginLayoutParams
    } catch (exception: Exception) {
        exception.printStackTrace(System.err)
    }
}