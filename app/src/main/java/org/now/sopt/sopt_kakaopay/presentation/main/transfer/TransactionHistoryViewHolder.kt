package org.now.sopt.sopt_kakaopay.presentation.main.transfer

import androidx.recyclerview.widget.RecyclerView
import org.now.sopt.sopt_kakaopay.R
import org.now.sopt.sopt_kakaopay.databinding.ItemRecentAccountBinding
import org.now.sopt.sopt_kakaopay.model.BookMarkRequestDto
import org.now.sopt.sopt_kakaopay.model.TransactionHistoryDto

class TransactionHistoryViewHolder(
    private val binding: ItemRecentAccountBinding,
    private val viewModel: TransferViewModel
) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(transactionHistory: TransactionHistoryDto) {
        with(binding) {
            tvRecentAccountUserName.text = transactionHistory.name
            tvRecentAccountUserNumber.text = transactionHistory.bankAccount
            tvRecentAccountUserBank.text = transactionHistory.bank
            ImageUtil.setBankImage(itemView.context, ivRecentUserBankLogo, transactionHistory.bank)

            if (transactionHistory.bookmark) {
                ivRecentUserFavorite.setImageResource(R.drawable.ic_star_full)
            } else {
                ivRecentUserFavorite.setImageResource(R.drawable.ic_star_empty)
            }

            ivRecentUserFavorite.setOnClickListener {
                val bookmarkRequest =
                    BookMarkRequestDto(transactionHistory.bank, transactionHistory.bankAccount)
                if (transactionHistory.bookmark) {
                    viewModel.deleteBookmark(bookmarkRequest)
                } else {
                    viewModel.addBookmark(bookmarkRequest)
                }
            }

        }
    }

}

