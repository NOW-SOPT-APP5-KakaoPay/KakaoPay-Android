package org.now.sopt.sopt_kakaopay.presentation.main.assets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.now.sopt.sopt_kakaopay.model.BalanceDto
import org.now.sopt.sopt_kakaopay.service.ApiService
import org.now.sopt.sopt_kakaopay.util.view.UiState

class AssetsViewModel(private val apiService: ApiService) : ViewModel() {

    private val _balanceUiState = MutableStateFlow<UiState<BalanceDto>>(UiState.Loading)
    val balanceUiState: StateFlow<UiState<BalanceDto>> get() = _balanceUiState

    fun fetchBalance() {
        viewModelScope.launch {
            _balanceUiState.value = UiState.Loading
            runCatching {
                val response = apiService.fetchBalance()
                if (response.isSuccessful) {
                    val serverResponse = response.body()
                    if (serverResponse != null && serverResponse.data.balance.isNotEmpty()) {
                        UiState.Success(serverResponse.data)
                    } else {
                        UiState.Empty
                    }
                } else {
                    UiState.Error("Error: ${response.code()}")
                }
            }.onSuccess { state ->
                _balanceUiState.value = state
            }.onFailure { exception ->
                _balanceUiState.value = UiState.Error(exception.message)
            }
        }
    }
}
