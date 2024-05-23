package org.now.sopt.sopt_kakaopay.presentation.main.transfer

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import org.now.sopt.sopt_kakaopay.ServicePool
import org.now.sopt.sopt_kakaopay.databinding.ActivityTransferBinding
import org.now.sopt.sopt_kakaopay.util.context.showToast
import org.now.sopt.sopt_kakaopay.util.view.UiState

class TransferActivity : AppCompatActivity() {

    private val binding by lazy { ActivityTransferBinding.inflate(layoutInflater) }
    private val transactionHistoryAdapter = TransactionHistoryAdapter()
    private val viewModel: TransferViewModel by viewModels {
        TransferViewModelFactory(ServicePool.authService)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initializeRecyclerView()
        observeTransactionUiState()
        viewModel.fetchTransactionHistory()
    }

    private fun initializeRecyclerView() {
        binding.rvTransferRecent.adapter = transactionHistoryAdapter
        binding.rvTransferRecent.layoutManager = LinearLayoutManager(this)
    }

    private fun observeTransactionUiState() {
        lifecycleScope.launch {
            viewModel.transactionUiState.collect { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.rvTransferRecent.visibility = View.GONE
                    }

                    is UiState.Empty -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rvTransferRecent.visibility = View.VISIBLE
                        showToast("불러올 리스트가 없습니다.")
                    }

                    is UiState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rvTransferRecent.visibility = View.VISIBLE
                        transactionHistoryAdapter.submitList(uiState.data)
                    }

                    is UiState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rvTransferRecent.visibility = View.VISIBLE
                        Log.d("TransferActivity", uiState.message ?: "Unknown error")
                        showToast(uiState.message ?: "리스트를 불러올 수 없습니다.")
                    }
                }
            }
        }
    }

}
