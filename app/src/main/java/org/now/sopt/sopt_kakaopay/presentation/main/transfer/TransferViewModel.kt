package org.now.sopt.sopt_kakaopay.presentation.main.transfer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.now.sopt.sopt_kakaopay.model.BookMarkRequestDto
import org.now.sopt.sopt_kakaopay.model.BookMarkResponseDto
import org.now.sopt.sopt_kakaopay.model.TransactionHistoryDto
import org.now.sopt.sopt_kakaopay.service.ApiService
import org.now.sopt.sopt_kakaopay.util.view.UiState

class TransferViewModel(private val apiService: ApiService) : ViewModel() {

    private val _transactionUiState =
        MutableStateFlow<UiState<List<TransactionHistoryDto>>>(UiState.Loading)
    val transactionUiState: StateFlow<UiState<List<TransactionHistoryDto>>> get() = _transactionUiState

    fun fetchTransactionHistory() {
        viewModelScope.launch {
            _transactionUiState.value = UiState.Loading
            runCatching {
                val response = apiService.fetchTransactionHistory()
                if (response.isSuccessful) {
                    val serverResponse = response.body()
                    if (serverResponse != null && serverResponse.data.isNotEmpty()) {
                        UiState.Success(serverResponse.data)
                    } else {
                        UiState.Empty
                    }
                } else {
                    UiState.Error("Error: ${response.code()}")
                }
            }.onSuccess {
                _transactionUiState.value = it
            }.onFailure {
                _transactionUiState.value = UiState.Error(it.message)
            }
        }
    }

    fun addBookmark(bookmarkRequest: BookMarkRequestDto) {
        viewModelScope.launch {
            try {
                val response = apiService.addBookmark(bookmarkRequest)
                handleBookmarkResponse(response)
            } catch (e: Exception) {
                _transactionUiState.value = UiState.Error("Failed to add bookmark: ${e.message}")
            }
        }
    }

    fun deleteBookmark(bookmarkRequest: BookMarkRequestDto) {
        viewModelScope.launch {
            try {
                val response = apiService.deleteBookmark(bookmarkRequest)
                handleBookmarkResponse(response)
            } catch (e: Exception) {
                _transactionUiState.value = UiState.Error("Failed to delete bookmark: ${e.message}")
            }
        }
    }

    private fun handleBookmarkResponse(response: BookMarkResponseDto) {
        if (response.status == 200 || response.status == 201) {
            fetchTransactionHistory()
        } else {
            _transactionUiState.value = UiState.Error("Failed to toggle bookmark")
        }
    }
}
