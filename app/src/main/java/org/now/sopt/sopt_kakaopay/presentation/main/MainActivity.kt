package org.now.sopt.sopt_kakaopay.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.now.sopt.sopt_kakaopay.R
import org.now.sopt.sopt_kakaopay.databinding.ActivityMainBinding
import org.now.sopt.sopt_kakaopay.presentation.main.all.AllFragment
import org.now.sopt.sopt_kakaopay.presentation.main.assets.AssetsFragment
import org.now.sopt.sopt_kakaopay.presentation.main.benefits.BenefitsFragment
import org.now.sopt.sopt_kakaopay.presentation.main.home.HomeFragment
import org.now.sopt.sopt_kakaopay.presentation.main.stocks.StocksFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        initCurrentFragment()
        initBottomNavClickListener()
    }

    private fun initCurrentFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(binding.fcvHome.id)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction().add(R.id.fcv_home, HomeFragment()).commit()
        }
        // 초기 선택 항목을 home으로 설정
        binding.bnvHome.selectedItemId = R.id.menu_home
    }

    // Bottom Navigation 클릭 이벤트
    private fun initBottomNavClickListener() {
        binding.bnvHome.setOnItemSelectedListener { menuItem ->
            val fragment = when (menuItem.itemId) {
                R.id.menu_assets -> AssetsFragment()
                R.id.menu_benefits -> BenefitsFragment()
                R.id.menu_home -> HomeFragment()
                R.id.menu_stocks -> StocksFragment()
                R.id.menu_all -> AllFragment()
                else -> null
            }
            fragment?.let {
                replaceFragment(it)
                true // 명시적 true return
            } ?: false // Fragment가 null일 경우 false
        }
    }

    // replaceFragment
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_home, fragment)
            .commit()
    }
}
