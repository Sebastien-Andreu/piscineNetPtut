package fr.iut.piscinenetptut.library.extension

import android.util.Log
import fr.iut.piscinenetptut.BuildConfig
import java.util.UUID

private val TAG = "StringExtension"

const val TIME_SECOND_MAX = 60000L
const val TIME_MINUTE_MAX = TIME_SECOND_MAX * 60
const val TIME_HOUR_MAX = TIME_MINUTE_MAX * 24
const val TIME_DAY_MAX = TIME_HOUR_MAX * 7
const val TIME_WEEK_MAX = TIME_DAY_MAX * 4
const val TIME_MONTH_MAX = TIME_WEEK_MAX * 12

fun String.toDebug(contextTag: String? = null) {
    try {
        if (BuildConfig.DEBUG) {
            if (null == contextTag) Log.w("DEBUG", this)
            else Log.w("DEBUG $contextTag", this)
        }
    } catch (exception: Exception) {
        exception.toTreatFor(TAG)
    }
}

fun createUniqueIdV4(): String {
    // v4 of uuid -> https://kodejava.org/how-do-i-generate-uuid-guid-in-java/
    // https://www.ietf.org/rfc/rfc4122.txt
    return UUID.randomUUID().toString()
}