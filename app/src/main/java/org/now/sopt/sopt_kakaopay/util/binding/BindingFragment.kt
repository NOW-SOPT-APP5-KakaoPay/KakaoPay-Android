package org.now.sopt.sopt_kakaopay.util.binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BindingFragment<T : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int // Layout Resource ID를 인자로 받음
) : Fragment() {
    private var _binding: T? = null // ViewDataBinding 객체를 null로 초기화

    protected val binding: T // binding 프로퍼티를 통해 _binding에 접근, null일 경우 예외 발생
        get() = requireNotNull(_binding) { "바인딩 객체 좀 생성해주세요 제발!!" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false) // 데이터 바인딩 설정
        binding.lifecycleOwner = viewLifecycleOwner // lifecycleOwner를 현재 Fragment의 viewLifecycleOwner로 설정
        return binding.root
    }

    // 프래그먼트의 뷰가 파괴될 때 호출되는 메서드
    override fun onDestroyView() {
        _binding = null // _binding = null로 설정해서 메모리 누수 방지
        super.onDestroyView()
    }
}
