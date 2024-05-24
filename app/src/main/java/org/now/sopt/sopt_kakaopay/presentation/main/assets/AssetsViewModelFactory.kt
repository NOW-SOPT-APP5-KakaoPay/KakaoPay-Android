package org.now.sopt.sopt_kakaopay.presentation.main.assets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.now.sopt.sopt_kakaopay.service.ApiService

class AssetsViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AssetsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AssetsViewModel(apiService) as T
        }
        throw IllegalArgumentException("알 수 없는 ViewModel 클래스")
    }
}

