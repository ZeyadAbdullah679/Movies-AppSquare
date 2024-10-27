package com.company.movies.util.screenDimensions

import android.content.Context
import android.util.DisplayMetrics


fun getScreenHeight(context: Context): Float {
    val displayMetrics: DisplayMetrics = context.resources.displayMetrics
    return displayMetrics.heightPixels / displayMetrics.density
}