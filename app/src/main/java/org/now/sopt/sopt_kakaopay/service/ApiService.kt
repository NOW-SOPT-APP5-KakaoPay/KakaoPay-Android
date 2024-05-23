package org.now.sopt.sopt_kakaopay.service

import org.now.sopt.sopt_kakaopay.MemberIdInterceptor
import org.now.sopt.sopt_kakaopay.model.BookMarkRequestDto
import org.now.sopt.sopt_kakaopay.model.BookMarkResponseDto
import org.now.sopt.sopt_kakaopay.model.TransactionHistoryResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("api/v1/history")
    suspend fun fetchTransactionHistory(): Response<TransactionHistoryResponseDto>

    @POST("api/v1/bookmark")
    suspend fun addBookmark(
        @Body bookmarkRequest: BookMarkRequestDto
    ): BookMarkResponseDto

    @DELETE("api/v1/bookmark")
    suspend fun deleteBookmark(
        @Query("bank") bank: String,
        @Query("bankAccount") bankAccount: String
    ): BookMarkResponseDto

}
