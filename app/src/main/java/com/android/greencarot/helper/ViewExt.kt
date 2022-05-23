package com.android.greencarot.helper

import android.view.View

fun View.show(visible: Boolean) {
    if (visible) visibility = View.VISIBLE else visibility = View.GONE
}