package org.now.sopt.sopt_kakaopay.presentation.main.assets

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.now.sopt.sopt_kakaopay.R
import org.now.sopt.sopt_kakaopay.ServicePool
import org.now.sopt.sopt_kakaopay.databinding.FragmentAssetsBinding
import org.now.sopt.sopt_kakaopay.databinding.FragmentBenefitsBinding
import org.now.sopt.sopt_kakaopay.presentation.main.transfer.TransferViewModel
import org.now.sopt.sopt_kakaopay.presentation.main.transfer.TransferViewModelFactory
import org.now.sopt.sopt_kakaopay.util.fragment.showToast
import org.now.sopt.sopt_kakaopay.util.view.UiState

class AssetsFragment : Fragment() {

    private var _binding: FragmentAssetsBinding? = null
    private val binding: FragmentAssetsBinding
        get() = requireNotNull(_binding) { "바인딩 객체 좀 생성해주세요 제발!!" }

    private val viewModel: AssetsViewModel by viewModels {
        AssetsViewModelFactory(ServicePool.authService)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAssetsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeBalanceUiState()
        viewModel.fetchBalance()
    }

    private fun observeBalanceUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.balanceUiState.collect { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        binding.tvAssetSaved.visibility = View.GONE
                    }

                    is UiState.Empty -> {
                        binding.tvAssetSaved.visibility = View.VISIBLE
                        showToast("불러올 잔액이 없습니다.")
                    }

                    is UiState.Success -> {
                        binding.tvAssetSaved.visibility = View.VISIBLE
                        binding.tvAssetSaved.text = uiState.data.payMoney // 데이터를 설정
                    }

                    is UiState.Error -> {
                        binding.tvAssetSaved.visibility = View.VISIBLE
                        Log.d("AssetsFragment", uiState.message ?: "Unknown error")
                        showToast(uiState.message ?: "내돈을 불러올 수 없습니다.")
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
