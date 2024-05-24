package org.now.sopt.sopt_kakaopay.presentation.main.home

import androidx.recyclerview.widget.RecyclerView
import org.now.sopt.sopt_kakaopay.databinding.ItemTotalContentBinding

class TotalContentViewHolder(private val binding: ItemTotalContentBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: TotalContentItem) {
        with(binding) {
            tvTotalContentName.text = item.name
            tvTotalContentDate.text = item.date
            tvTotalContentCost.text = item.cost
            tvTotalContentSendOrDeposit.text = item.sendOrDeposit
            ivTotalContentBankLogo.setImageResource(item.imageResId)
        }
    }
}
