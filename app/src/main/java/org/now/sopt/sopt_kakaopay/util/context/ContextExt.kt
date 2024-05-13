package org.now.sopt.sopt_kakaopay.util.context

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

// 토스트 메시지
fun Context.showToast(message: String, isShort: Boolean = true) {
    val duration = if (isShort) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
    Toast.makeText(this, message, duration).show()
}

// 키보드 숨기기
fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

// 문자열 리소스 가져오기
fun Context.stringOf(@StringRes resId: Int) = getString(resId)

// 색상 리소스 가져오기
fun Context.colorOf(@ColorRes resId: Int) = ContextCompat.getColor(this, resId)

// 드로어블 리소스 가져오기
fun Context.drawableOf(@DrawableRes resId: Int) = ContextCompat.getDrawable(this, resId)
