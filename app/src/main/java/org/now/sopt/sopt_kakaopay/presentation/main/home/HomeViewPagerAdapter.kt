package org.now.sopt.sopt_kakaopay.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.now.sopt.sopt_kakaopay.databinding.ItemTotalContentBinding

class HomeViewPagerAdapter(private val items: List<TotalContentItem>) :
    RecyclerView.Adapter<HomeViewPagerAdapter.HomeViewPagerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewPagerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTotalContentBinding.inflate(inflater, parent, false)
        binding.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        return HomeViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewPagerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class HomeViewPagerViewHolder(private val binding: ItemTotalContentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TotalContentItem) {
            binding.tvTotalContentName.text = item.name
            binding.tvTotalContentDate.text = item.date
            binding.tvTotalContentCost.text = item.cost
            binding.tvTotalContentSendOrDeposit.text = item.sendOrDeposit
            binding.ivTotalContentBankLogo.setImageResource(item.imageResId)
        }
    }
}

data class TotalContentItem(
    val name: String,
    val date: String,
    val cost: String,
    val sendOrDeposit: String,
    val imageResId: Int
)
