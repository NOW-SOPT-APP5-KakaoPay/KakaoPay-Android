package org.now.sopt.sopt_kakaopay.presentation.main.transfer

import androidx.recyclerview.widget.RecyclerView
import org.now.sopt.sopt_kakaopay.databinding.ItemRecentAccountBinding
import org.now.sopt.sopt_kakaopay.model.TransactionHistoryDto

class TransactionHistoryViewHolder(private val binding: ItemRecentAccountBinding): RecyclerView.ViewHolder(binding.root) {
    fun onBind(transactionHistory: TransactionHistoryDto) {
        with(binding) {
            tvRecentAccountUserName.text = transactionHistory.name
            tvRecentAccountUserNumber.text = transactionHistory.bankAccount
            tvRecentAccountUserBank.text = transactionHistory.bank
            ImageUtil.setBankImage(itemView.context, ivRecentUserBankLogo, transactionHistory.bank)
        }
    }
}
