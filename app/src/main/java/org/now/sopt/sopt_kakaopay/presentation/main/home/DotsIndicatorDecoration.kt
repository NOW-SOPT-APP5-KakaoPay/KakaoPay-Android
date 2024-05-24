package org.now.sopt.sopt_kakaopay

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.now.sopt.sopt_kakaopay.util.dp

// CustomView - Indicator
class DotsIndicatorDecoration(
    @ColorInt colorInactive: Int,
    @ColorInt colorActive: Int
) : RecyclerView.ItemDecoration() {

    private val indicatorHeight: Int = 20.dp // Indicator 높이
    private val indicatorItemPadding: Int = 4.dp // Indicator 패딩
    private val radius: Int = 3.dp // Indicator 점 반지름 6 / 2 = 3
    private val itemIndicatorMargin: Int = 10.dp // RecyclerView 랑 Indicator 사이 10dp

    private val inactivePaint = createPaint(colorInactive) // 비활성 - 페인트 객체
    private val activePaint = createPaint(colorActive) // 활성 - 페인트 객체

    // Paint 객체를 초기화하는 메서드
    private fun createPaint(color: Int): Paint {
        return Paint().apply {
            style = Paint.Style.FILL // 스타일 설정
            isAntiAlias = true // 안티 앨리어싱 설정
            this.color = color // 색상 설정
        }
    }

    // RecyclerViewItem 위에 Indicator 그리는 메서드
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val adapter = parent.adapter ?: return // data set 없으면 return
        val itemCount = adapter.itemCount // 아이템 개수 가져오기

        // Indicator 총 길이 계산
        val totalLength = (this.radius * 2 * itemCount).toFloat() // Indicator 지름 * 아이템 개수
        val paddingBetweenItems = (0.coerceAtLeast(itemCount - 1) * indicatorItemPadding).toFloat() // 0보다 큰 값이면 그대로 반환, 아니면 0 반환
        val indicatorTotalWidth = totalLength + paddingBetweenItems // Indicator 전체 너비 계산
        val indicatorStartX = (parent.width - indicatorTotalWidth) / 2f // Indicator 시작 X 위치 계산
        val indicatorPosY = parent.height - indicatorHeight / 2f // Indicator Y 위치 계산

        // 현재 활성화된 Item 위치 가져오기
        val activePosition: Int = (parent.layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition()
            ?: return // 첫번째 보이는 아이템 위치, 지원하지 않는 LayoutManager 사용 시 return

        if (activePosition == RecyclerView.NO_POSITION) return // 활성화된 아이템이 없으면 return

        drawInactiveDots(c, indicatorStartX, indicatorPosY, itemCount, activePosition) // 비활성 Indicator 그리기
        drawActiveDot(c, indicatorStartX, indicatorPosY, activePosition) // 활성 Indicator 그리기
    }

    // 비활성 상태 Indicator 그리는 메서드
    private fun drawInactiveDots(
        c: Canvas,
        indicatorStartX: Float,
        indicatorPosY: Float,
        itemCount: Int,
        activeIndex: Int,
    ) {
        // Item Indicator 너비 계산 (패딩 포함)
        val itemWidth = (this.radius * 2 + indicatorItemPadding).toFloat()
        var start = indicatorStartX + radius
        for (i in 0 until itemCount) {
            if (i == activeIndex) {
                start += itemWidth
                continue
            }
            // 비활성 Indicator 그리기
            c.drawCircle(start, indicatorPosY, radius.toFloat(), inactivePaint)
            start += itemWidth
        }
    }

    // 활성 상태 Indicator 그리는 메서드
    private fun drawActiveDot(
        c: Canvas, indicatorStartX: Float, indicatorPosY: Float,
        highlightPosition: Int
    ) {
        // Item Indicator 너비 계산 (패딩 포함)
        val itemWidth = (radius * 2 + indicatorItemPadding).toFloat()
        val highlightStart = indicatorStartX + itemWidth * highlightPosition + radius
        c.drawCircle(highlightStart, indicatorPosY, radius.toFloat(), activePaint) // 활성 Indicator 그리기
    }

    // 아이템 간 간격 설정 메서드
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = indicatorHeight + itemIndicatorMargin // Indicator 높이 + 아이템 간 간격
    }
}
