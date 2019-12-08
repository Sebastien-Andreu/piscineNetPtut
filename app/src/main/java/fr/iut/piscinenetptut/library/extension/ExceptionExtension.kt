package fr.iut.piscinenetptut.library.extension

import fr.iut.piscinenetptut.BuildConfig.DEBUG


fun Exception.toTreatFor(containerTag: String) {
    // sometimes localized message is null :/
    this.localizedMessage?.let {
        it.toDebug()
    }

    if (DEBUG) {
        System.out.println("An exception ${this.message} occured with $containerTag: $this \n ${this.stackTrace}")
        this.printStackTrace()
    }
}

fun Throwable.toTreatFor(
    containerTag: String
) {
    if (DEBUG) {
        this.printStackTrace()
    }
}