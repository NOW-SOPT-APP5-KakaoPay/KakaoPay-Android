package org.now.sopt.sopt_kakaopay.presentation.main.payment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.now.sopt.sopt_kakaopay.R
import org.now.sopt.sopt_kakaopay.ServicePool
import org.now.sopt.sopt_kakaopay.databinding.BottomFragmentPaymentBinding

class PaymentBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: BottomFragmentPaymentBinding? = null
    private val binding: BottomFragmentPaymentBinding
        get() = requireNotNull(_binding) { "바인딩 객체 좀 생성해주세요 제발!!" }

    private var originalStatusBarColor: Int = 0

    private val viewModel: PaymentViewModel by viewModels {
        PaymentViewModelFactory(ServicePool.authService)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomFragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCustomSwitch()
        setupOptions()
        viewModel.payMoneyData.observe(viewLifecycleOwner, Observer { payMoney ->
            binding.tvPaymentPayMoneyAmount.text = payMoney
        })
        viewModel.payPointData.observe(viewLifecycleOwner, Observer { payPoint ->
            binding.tvPaymentPayMoneyAmount.text = payPoint
        })
        viewModel.fetchPayPoint()
        viewModel.fetchPayMoney()
    }

    private fun setupCustomSwitch() {
        with(binding.scPaymentPayPointActivate) {
            trackDrawable = ContextCompat.getDrawable(context, R.drawable.sw_selector_track)
            thumbDrawable = ContextCompat.getDrawable(context, R.drawable.sw_selector_thumb)
        }
    }

    private fun setupOptions() {
        with(binding.includeSwitch) {
            tvOptionBarcode.isSelected = true
            tvOptionQrcode.isSelected = false

            tvOptionBarcode.setOnClickListener {
                setSelectedOption(tvOptionBarcode, tvOptionQrcode)
            }

            tvOptionQrcode.setOnClickListener {
                setSelectedOption(tvOptionQrcode, tvOptionBarcode)
            }
        }
    }

    private fun setSelectedOption(selected: View, unselected: View) {
        selected.isSelected = true
        unselected.isSelected = false
    }

    override fun onResume() {
        super.onResume()
        originalStatusBarColor = requireActivity().window.statusBarColor
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.yellow)
    }

    override fun onDestroyView() {
        requireActivity().window.statusBarColor = originalStatusBarColor
        _binding = null
        super.onDestroyView()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object : BottomSheetDialog(requireContext(), theme) {
            override fun onAttachedToWindow() {
                super.onAttachedToWindow()
                val bottomSheet = findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
                val behavior = BottomSheetBehavior.from(bottomSheet)
                bottomSheet.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                behavior.peekHeight = 0

            }
        }.apply {
            setOnDismissListener {
                requireActivity().window.statusBarColor = originalStatusBarColor
            }
        }
    }

}
