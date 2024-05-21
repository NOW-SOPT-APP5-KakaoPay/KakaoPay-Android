package org.now.sopt.sopt_kakaopay.presentation.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.now.sopt.sopt_kakaopay.R
import org.now.sopt.sopt_kakaopay.databinding.FragmentHomeBinding
import org.now.sopt.sopt_kakaopay.util.binding.BindingFragment

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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
        setupViewPager()
    }

    private fun setupViewPager() {
        val adapter = HomeViewPagerAdapter(items)
        binding.vpHomeTotalContent.adapter = adapter
        binding.vpHomeTotalContentDotsIndicator.attachTo(binding.vpHomeTotalContent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

