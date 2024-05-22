package org.now.sopt.sopt_kakaopay.presentation.main.transfer

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import org.now.sopt.sopt_kakaopay.R
import org.now.sopt.sopt_kakaopay.util.context.drawableOf

object ImageUtil {
    @DrawableRes
    fun getBankDrawable(bank: String): Int {
        return when (bank) {
            "신한" -> R.drawable.img_shinhan_logo
            "토스뱅크" -> R.drawable.img_toss_logo
            else -> R.drawable.ic_kakaobank_logo // 기본 이미지
        }
    }

    fun setBankImage(context: Context, imageView: ImageView, bank: String) {
        val drawable = context.drawableOf(getBankDrawable(bank))
        drawable?.let {
            imageView.setImageDrawable(it)
        }
    }
}
