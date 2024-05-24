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
        _binding = null
        super.onDestroyView()
    }
}
