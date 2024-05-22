package org.now.sopt.sopt_kakaopay.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePayMoneyDto(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: PayMoneyData
)
@Serializable
data class PayMoneyData(
    @SerialName("payMoney")
    val PayMoney: String
)
