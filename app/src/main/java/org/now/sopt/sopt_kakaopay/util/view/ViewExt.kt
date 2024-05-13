package org.now.sopt.sopt_kakaopay.util.view

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.google.android.material.snackbar.Snackbar

// 단일 클릭 리스너
inline fun View.setOnSingleClickListener(
    delay: Long = 500L, // 기본 클릭 간격을 500 밀리세컨
    crossinline block: (View) -> Unit // 클릭 시 실행할 람다 블록
) {
    var isClickable = true // 클릭 가능 여부
    setOnClickListener { view ->
        if (isClickable) {
            isClickable = false // 클릭 가능 여부를 false로
            block(view) // 전달받은 람다 블록 실행
            // 지연 시간 후에 클릭 가능 여부를 다시 true로
            view.postDelayed({
                isClickable = true
            }, delay)
        }
    }
}

// 스낵바 메시지
fun View.showSnackBar(message: String, isShort: Boolean = true) {
    val duration = if (isShort) Snackbar.LENGTH_SHORT else Snackbar.LENGTH_LONG
    Snackbar.make(this, message, duration).show()
}

// DiffUtil.ItemCallback을 상속하는 제네릭 클래스, Adapter에서 아이템 비교할 때 쓰면 됨
class ItemDiffCallback<T : Any>(
    val onItemsTheSame: (T, T) -> Boolean, // 아이템 비교를 위한 람다 함수
    val onContentsTheSame: (T, T) -> Boolean, // 아이템 내용 비교를 위한 람다 함수
) : DiffUtil.ItemCallback<T>() {
    // 같은 아이템인지 비교
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = onItemsTheSame(oldItem, newItem)

    // 내용이 같은지 비교
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        onContentsTheSame(oldItem, newItem)
}

