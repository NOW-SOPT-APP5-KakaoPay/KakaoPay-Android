package org.now.sopt.sopt_kakaopay.service

import org.now.sopt.sopt_kakaopay.model.BalanceResponseDto
import org.now.sopt.sopt_kakaopay.model.BookMarkRequestDto
import org.now.sopt.sopt_kakaopay.model.BookMarkResponseDto
import org.now.sopt.sopt_kakaopay.model.ResponsePayMoneyDto
import org.now.sopt.sopt_kakaopay.model.ResponsePayPointDto
import org.now.sopt.sopt_kakaopay.model.TransactionHistoryResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST

interface ApiService {
    @GET("api/v1/history")
    suspend fun fetchTransactionHistory(): Response<TransactionHistoryResponseDto>

    @GET("api/v1/member/balance")
    suspend fun fetchBalance(): Response<BalanceResponseDto>

    @POST("api/v1/bookmark")
    suspend fun addBookmark(
        @Body bookmarkRequest: BookMarkRequestDto
    ): BookMarkResponseDto

    @HTTP(method = "DELETE", path = "api/v1/bookmark", hasBody = true)
    suspend fun deleteBookmark(
        @Body bookmarkRequest: BookMarkRequestDto
    ): BookMarkResponseDto

    @GET("api/v1/member/paypoint")
    suspend fun getPayPoint(
    ): ResponsePayPointDto

    @GET("api/v1/member/paymoney")
    suspend fun getPayMoney(
    ): ResponsePayMoneyDto
}
