package org.now.sopt.sopt_kakaopay

import okhttp3.Interceptor
import okhttp3.Response

class MemberIdInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .header("memberId", MEMBER_ID)
            .build()
        return chain.proceed(newRequest)
    }
    companion object {
        const val MEMBER_ID = "2"
    }
}
