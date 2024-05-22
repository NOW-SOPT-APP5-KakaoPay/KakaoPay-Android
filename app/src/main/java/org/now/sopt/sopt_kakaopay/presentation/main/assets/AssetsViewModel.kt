package org.now.sopt.sopt_kakaopay.presentation.main.assets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.now.sopt.sopt_kakaopay.service.ServicePool

class AssetsViewModel : ViewModel() {

    private val _PayPointData = MutableLiveData<String>()
    val PayPointData: LiveData<String> get() = _PayPointData
    private val _PayMoneyData = MutableLiveData<String>()
    val PayMoneyData: LiveData<String> get() = _PayMoneyData

    fun fetchPayPoint() {
        viewModelScope.launch {
            try {
                val response = ServicePool.apiService.getPayPoint("2")
                _PayPointData.postValue(response.data.PayPoint)
            } catch (e: Exception) {
                _PayPointData.postValue("Error: ${e.message}")
            }
        }
    }
    fun fetchPayMoney() {
        viewModelScope.launch {
            try {
                val response = ServicePool.apiService.getPayMoney("2")
                _PayMoneyData.postValue(response.data.PayMoney)
            } catch (e: Exception) {
                _PayMoneyData.postValue("Error: ${e.message}")
            }
        }
    }
}

