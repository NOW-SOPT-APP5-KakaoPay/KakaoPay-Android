package org.now.sopt.sopt_kakaopay.util.binding

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BindingActivity<T : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int // Layout Resource ID를 인자로 받음
) : AppCompatActivity() {
    protected lateinit var binding: T // ViewDataBinding 객체를 지연 초기화로 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId) // 데이터 바인딩 설정
        binding.lifecycleOwner = this // lifecycleOwner를 현재 액티비티로 설정
    }
}
