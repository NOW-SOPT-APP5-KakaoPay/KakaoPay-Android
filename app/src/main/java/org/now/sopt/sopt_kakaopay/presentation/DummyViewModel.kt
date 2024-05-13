package org.now.sopt.sopt_kakaopay.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import org.now.sopt.sopt_kakaopay.model.RequestDto
import org.now.sopt.sopt_kakaopay.util.view.UiState

class DummyViewModel() : ViewModel() {
    private val requestDto = RequestDto(id = 123)

    val data = liveData(Dispatchers.IO) {
        emit(UiState.Loading)
        try {
        } catch (_: Exception) {
        }
    }
}
