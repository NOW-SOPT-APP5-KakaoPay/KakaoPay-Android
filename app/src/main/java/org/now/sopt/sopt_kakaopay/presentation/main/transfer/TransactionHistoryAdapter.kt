package org.now.sopt.sopt_kakaopay.presentation.main.transfer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.now.sopt.sopt_kakaopay.databinding.ItemRecentAccountBinding
import org.now.sopt.sopt_kakaopay.model.TransactionHistoryDto
import org.now.sopt.sopt_kakaopay.util.view.ItemDiffCallback

class TransactionHistoryAdapter(private val viewModel: TransferViewModel) :
    ListAdapter<TransactionHistoryDto, TransactionHistoryViewHolder>(
        ItemDiffCallback(
            onItemsTheSame = { oldItem, newItem -> oldItem.bankAccount == newItem.bankAccount },
            onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
        )
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionHistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecentAccountBinding.inflate(inflater, parent, false)
        return TransactionHistoryViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: TransactionHistoryViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}


