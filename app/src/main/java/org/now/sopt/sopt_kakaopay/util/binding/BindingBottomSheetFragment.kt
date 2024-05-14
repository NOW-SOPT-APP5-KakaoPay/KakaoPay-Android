package org.now.sopt.sopt_kakaopay.util.binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BindingBottomSheetFragment<T : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int // Layout Resource ID를 인자로 받음
) : BottomSheetDialogFragment() {
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    protected abstract fun initView()

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
