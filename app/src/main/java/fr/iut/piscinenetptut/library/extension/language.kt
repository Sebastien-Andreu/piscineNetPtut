package fr.iut.piscinenetptut.library.extension

import android.content.res.Configuration
import android.content.res.Resources
import android.util.DisplayMetrics
import fr.iut.piscinenetptut.entities.Language
import java.util.*

fun setAppLocale(resources: Resources) {
    val dm: DisplayMetrics = resources.displayMetrics
    val config: Configuration = resources.configuration
    config.setLocale(Locale(Language.language))
    resources.updateConfiguration(config, dm)
}