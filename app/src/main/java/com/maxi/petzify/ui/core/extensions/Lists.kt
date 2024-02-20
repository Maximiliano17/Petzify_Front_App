package com.maxi.petzify.ui.core.extensions

import android.widget.EditText

fun List<EditText>.haveContent() = this.all { it.text?.isNotEmpty() ?: false }