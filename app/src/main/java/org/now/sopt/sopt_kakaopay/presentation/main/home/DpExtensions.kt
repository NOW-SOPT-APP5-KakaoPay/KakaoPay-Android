package org.now.sopt.sopt_kakaopay.util

import android.content.res.Resources
import kotlin.math.roundToInt

// dp값 px값으로 변환
val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).roundToInt()
