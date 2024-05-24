package org.now.sopt.sopt_kakaopay.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import org.now.sopt.sopt_kakaopay.R
import org.now.sopt.sopt_kakaopay.databinding.FragmentPaymentBinding

class PaymentFragment : Fragment() {

    private var _binding: FragmentPaymentBinding? = null
    private val binding: FragmentPaymentBinding
        get() = requireNotNull(_binding) { "바인딩 객체 좀 생성해주세요 제발!!" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCustomSwitch()
        setupOptions()
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
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.yellow)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
