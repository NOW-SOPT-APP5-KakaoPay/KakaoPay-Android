package org.now.sopt.sopt_kakaopay.presentation.main.transfer

import androidx.recyclerview.widget.RecyclerView
import org.now.sopt.sopt_kakaopay.R
import org.now.sopt.sopt_kakaopay.databinding.ItemRecentAccountBinding
import org.now.sopt.sopt_kakaopay.model.TransactionHistoryDto

class TransactionHistoryViewHolder(
    private val binding: ItemRecentAccountBinding,
    private val bookmarkClickListener: (TransactionHistoryDto) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(transactionHistory: TransactionHistoryDto) {
        with(binding) {
            tvRecentAccountUserName.text = transactionHistory.name
            tvRecentAccountUserNumber.text = transactionHistory.bankAccount
            tvRecentAccountUserBank.text = transactionHistory.bank
            ImageUtil.setBankImage(itemView.context, ivRecentUserBankLogo, transactionHistory.bank)

            ivRecentUserFavorite.setImageResource(
                if (transactionHistory.bookmark) R.drawable.ic_star_full
                else R.drawable.ic_star_empty
            )

            ivRecentUserFavorite.setOnClickListener {
                bookmarkClickListener(transactionHistory)
            }


        }
    }
}

