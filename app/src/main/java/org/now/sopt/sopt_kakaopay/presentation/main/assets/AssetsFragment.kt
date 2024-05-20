package org.now.sopt.sopt_kakaopay.presentation.main.assets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.now.sopt.sopt_kakaopay.R
import org.now.sopt.sopt_kakaopay.databinding.FragmentAssetsBinding
import org.now.sopt.sopt_kakaopay.databinding.FragmentBenefitsBinding

class AssetsFragment : Fragment() {

    private var _binding: FragmentAssetsBinding? = null
    private val binding: FragmentAssetsBinding
        get() = requireNotNull(_binding) { "바인딩 객체 좀 생성해주세요 제발!!" }

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
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
