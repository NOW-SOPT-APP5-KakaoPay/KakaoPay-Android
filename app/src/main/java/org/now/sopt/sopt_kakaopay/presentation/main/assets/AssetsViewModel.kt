package org.now.sopt.sopt_kakaopay.presentation.main.assets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.now.sopt.sopt_kakaopay.service.ApiService

class AssetsViewModel(private val apiService: ApiService) : ViewModel() {

    private val _payPointData = MutableLiveData<String>()
    val payPointData: LiveData<String> get() = _payPointData
    private val _payMoneyData = MutableLiveData<String>()
    val payMoneyData: LiveData<String> get() = _payMoneyData

    fun fetchPayPoint() {
        viewModelScope.launch {
            try {
                val response = apiService.getPayPoint()
                _payPointData.postValue(response.data.PayPoint)
            } catch (e: Exception) {
                _payPointData.postValue("Error: ${e.message}")
            }
        }
    }

    fun fetchPayMoney() {
        viewModelScope.launch {
            try {
                val response = apiService.getPayMoney()
                _payMoneyData.postValue(response.data.PayMoney)
            } catch (e: Exception) {
                _payMoneyData.postValue("Error: ${e.message}")
            }
        }
    }
}
