package org.now.sopt.sopt_kakaopay.presentation.main.transfer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import org.now.sopt.sopt_kakaopay.ServicePool
import org.now.sopt.sopt_kakaopay.databinding.FragmentTransferBinding
import org.now.sopt.sopt_kakaopay.util.fragment.showToast
import org.now.sopt.sopt_kakaopay.util.view.UiState

class TransferFragment : Fragment() {

    private var _binding: FragmentTransferBinding? = null
    private val binding: FragmentTransferBinding
        get() = requireNotNull(_binding) { "바인딩 객체 좀 생성해주세요 제발!!" }

    private val transactionHistoryAdapter = TransactionHistoryAdapter()
    private val viewModel: TransferViewModel by viewModels {
        TransferViewModelFactory(ServicePool.authService)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransferBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()
        observeTransactionUiState()
        viewModel.fetchTransactionHistory()
    }

    private fun initializeRecyclerView() {
        binding.rvTransferRecent.adapter = transactionHistoryAdapter
        binding.rvTransferRecent.layoutManager = LinearLayoutManager(context)
    }

    private fun observeTransactionUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
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
                        Log.d("TransferFragment", uiState.message ?: "Unknown error")
                        showToast(uiState.message ?: "리스트를 불러올 수 없습니다.")
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
