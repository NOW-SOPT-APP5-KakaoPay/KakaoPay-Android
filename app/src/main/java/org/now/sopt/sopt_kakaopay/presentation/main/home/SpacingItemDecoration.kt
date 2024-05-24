package org.now.sopt.sopt_kakaopay.presentation.main.home

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

// RecyclerView 아이템 간 수평 간격 설정용 클래스
class SpacingItemDecoration(private val horizontalSpacing: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view) // 현재 뷰의 어댑터 위치를 가져오기
        if (position != RecyclerView.NO_POSITION) { // 어댑터 위치가 없는 경우
            outRect.right = horizontalSpacing // 오른쪽 간격 설정
        }
    }
}
