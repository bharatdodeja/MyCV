package com.bharatdodeja.mycv.framework.util

import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import android.view.View

inline fun View.snackBar(@StringRes messageRes: Int, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
    snackBar(resources.getString(messageRes), length, f)
}

inline fun View.snackBar(message: String, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
    Snackbar.make(this, message, length).also {
        it.f()
        it.show()
    }
}

fun Snackbar.action(@StringRes actionRes: Int, color: Int? = null, listener: (View) -> Unit) {
    action(view.resources.getString(actionRes), color, listener)
}

fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(color) }
}