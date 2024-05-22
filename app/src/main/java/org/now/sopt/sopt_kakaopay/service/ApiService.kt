package org.now.sopt.sopt_kakaopay.service

import org.now.sopt.sopt_kakaopay.model.ResponsePayMoneyDto
import org.now.sopt.sopt_kakaopay.model.ResponsePayPointDto
import org.now.sopt.sopt_kakaopay.model.TransactionHistoryResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("api/v1/history")
    suspend fun fetchTransactionHistory(): Response<TransactionHistoryResponseDto>
    @GET("api/v1/member/paypoint")
    suspend fun getPayPoint(
        @Header("memberId") memberId: String
    ): ResponsePayPointDto

    @GET("api/v1/member/paymoney")
    suspend fun getPayMoney(
        @Header("memberId") memberId: String
    ): ResponsePayMoneyDto
}
