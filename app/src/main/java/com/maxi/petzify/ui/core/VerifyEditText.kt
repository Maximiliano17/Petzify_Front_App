package com.maxi.petzify.ui.core

import android.widget.EditText

object VerifyEditText {
    fun haveContent(editTexts:List<EditText>) = editTexts.all { it.text?.isNotEmpty() ?: false }

}