package org.now.sopt.sopt_kakaopay.presentation.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import org.now.sopt.sopt_kakaopay.DotsIndicatorDecoration
import org.now.sopt.sopt_kakaopay.R
import org.now.sopt.sopt_kakaopay.databinding.FragmentHomeBinding
import org.now.sopt.sopt_kakaopay.presentation.PaymentBottomSheetFragment
import org.now.sopt.sopt_kakaopay.presentation.main.transfer.TransferActivity

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "바인딩 객체 좀 생성해주세요 제발!!" }

    private val items = listOf(
        TotalContentItem("연진카뱅", "05.20", "-20,000", "송금", R.drawable.ic_kakaobank_logo),
        TotalContentItem("문수카뱅", "05.21", "-19,900", "송금", R.drawable.ic_kakaobank_logo),
        TotalContentItem("하늘카뱅", "05.22", "+19,900", "입금", R.drawable.ic_kakaobank_logo),
        TotalContentItem("비상금", "05.24", "-100,000", "송금", R.drawable.ic_kakaobank_logo),
        TotalContentItem("주택청약", "05.25", "-50,000", "송금", R.drawable.ic_kakaobank_logo),
        TotalContentItem("생활비", "05.26", "+200,000", "입금", R.drawable.ic_kakaobank_logo)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupTransferButton()
        showPaymentBottomSheet()
    }

    private fun setupRecyclerView() {
        val adapter = TotalContentAdapter(items)
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.rvHomeTotalContent.adapter = adapter
        binding.rvHomeTotalContent.layoutManager = layoutManager

        // 아이템 간 간격 설정
        val spacing = resources.getDimensionPixelSize(R.dimen.item_spacing)
        binding.rvHomeTotalContent.addItemDecoration(SpacingItemDecoration(spacing))

        // DotsIndicatorDecoration 추가
        binding.rvHomeTotalContent.addItemDecoration(
            DotsIndicatorDecoration(
                colorInactive = ContextCompat.getColor(requireContext(), R.color.grey500),
                colorActive = ContextCompat.getColor(requireContext(), R.color.black)
            )
        )

        // PagerSnapHelper 추가
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvHomeTotalContent)
    }

    private fun setupTransferButton() {
        binding.btnHomePayMoneySend.setOnClickListener {
            navigateToTransferActivity()
        }
    }

    private fun navigateToTransferActivity() {
        val intent = Intent(requireContext(), TransferActivity::class.java)
        startActivity(intent)
    }
    private fun showPaymentBottomSheet() {
        binding.clHomeGoToSitePayment.setOnClickListener {
            val paymentBottomSheetFragment = PaymentBottomSheetFragment()
            paymentBottomSheetFragment.show(childFragmentManager, paymentBottomSheetFragment.tag)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
