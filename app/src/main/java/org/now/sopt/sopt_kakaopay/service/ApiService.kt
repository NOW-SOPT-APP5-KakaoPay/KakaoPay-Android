package org.now.sopt.sopt_kakaopay.service

import org.now.sopt.sopt_kakaopay.model.BalanceResponseDto
import org.now.sopt.sopt_kakaopay.model.TransactionHistoryResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api/v1/history")
    suspend fun fetchTransactionHistory(): Response<TransactionHistoryResponseDto>

    @GET("api/v1/member/balance")
    suspend fun fetchBalance(): Response<BalanceResponseDto>
}
