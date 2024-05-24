package org.now.sopt.sopt_kakaopay.presentation.main.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.now.sopt.sopt_kakaopay.service.ApiService

class PaymentViewModelFactory (private val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PaymentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PaymentViewModel(apiService) as T
        }
        throw IllegalArgumentException("알 수 없는 ViewModel 클래스")
    }
}
