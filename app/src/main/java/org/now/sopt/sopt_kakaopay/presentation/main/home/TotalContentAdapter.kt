package org.now.sopt.sopt_kakaopay.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.now.sopt.sopt_kakaopay.databinding.ItemTotalContentBinding

class TotalContentAdapter(private val items: List<TotalContentItem>) :
    RecyclerView.Adapter<TotalContentViewHolder>() {

    // ViewHolder를 생성하는 메서드
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TotalContentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTotalContentBinding.inflate(inflater, parent, false)
        return TotalContentViewHolder(binding)
    }

    // ViewHolder에 데이터를 바인딩하는 메서드
    override fun onBindViewHolder(holder: TotalContentViewHolder, position: Int) {
        holder.bind(items[position])
    }

    // 아이템 개수를 반환하는 메서드
    override fun getItemCount(): Int {
        return items.size
    }
}
