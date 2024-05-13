package org.now.sopt.sopt_kakaopay.presentation

import android.os.Bundle
import android.util.Log
import org.now.sopt.sopt_kakaopay.R
import org.now.sopt.sopt_kakaopay.databinding.ActivityDummyBinding
import org.now.sopt.sopt_kakaopay.util.binding.BindingActivity

class DummyActivity : BindingActivity<ActivityDummyBinding>(R.layout.activity_dummy) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity","call onCreate")

    }

    override fun onDestroy() {
        Log.d("MainActivity","call onDestroy")
        super.onDestroy()
    }
}
