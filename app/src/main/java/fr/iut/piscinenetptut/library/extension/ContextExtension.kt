package fr.iut.piscinenetptut.library.extension

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.core.content.ContextCompat

fun Context.getDrawableSafe(id: Int): Drawable? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        this.getDrawable(id)
    } else {
        ContextCompat.getDrawable(this, id)
    }
}

fun Context.getColorSafe(id: Int): Int? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        this.getColor(id)
    } else {
        ContextCompat.getColor(this, id)
    }
}

