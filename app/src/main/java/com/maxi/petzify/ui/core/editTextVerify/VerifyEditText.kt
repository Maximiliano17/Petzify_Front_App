package com.maxi.petzify.ui.core.editTextVerify

import android.widget.EditText
import javax.inject.Inject

object VerifyEditText {
    fun haveContent(editTexts:List<EditText>) = editTexts.all { it.text?.isNotEmpty() ?: false }

}