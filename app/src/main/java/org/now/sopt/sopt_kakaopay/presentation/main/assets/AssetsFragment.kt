package org.now.sopt.sopt_kakaopay.presentation.main.assets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import org.now.sopt.sopt_kakaopay.ServicePool
import org.now.sopt.sopt_kakaopay.databinding.FragmentAssetsBinding
import java.text.DecimalFormat

class AssetsFragment : Fragment() {

    private var _binding: FragmentAssetsBinding? = null
    private val binding: FragmentAssetsBinding
        get() = requireNotNull(_binding) { "바인딩 객체 좀 생성해주세요 제발!!" }

    private val viewModel: AssetsViewModel by viewModels {
        AssetsViewModelFactory(ServicePool.authService)
    }

    private var payMoney: String = ""
    private var payPoint: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAssetsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.payMoneyData.observe(viewLifecycleOwner, Observer { payMoney ->
            this.payMoney = payMoney
            updateAssetPointText()
        })

        viewModel.payPointData.observe(viewLifecycleOwner, Observer { payPoint ->
            this.payPoint = payPoint
            updateAssetPointText()
        })

        viewModel.fetchPayPoint()
        viewModel.fetchPayMoney()
    }

    private fun updateAssetPointText() {
        val payMoneyInt = payMoney.replace(",", "").toIntOrNull() ?: 0
        val payPointInt = payPoint.replace(",", "").toIntOrNull() ?: 0
        val total = payMoneyInt + payPointInt
        val decimalFormat = DecimalFormat("#,###")
        val totalMoney = decimalFormat.format(total)
        binding.txvAssetPoint.text = "$totalMoney 원"
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
