package com.guizaotech.listaseries.ui.extesion

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Activity.showToastError(message: String) {
    Toast.makeText(
            this,
            message,
            Toast.LENGTH_LONG
    ).show()
}

fun Fragment.showToastError(message: String) {
    Toast.makeText(
            this.requireContext(),
            message,
            Toast.LENGTH_LONG
    ).show()
}
