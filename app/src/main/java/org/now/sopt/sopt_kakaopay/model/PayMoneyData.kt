package org.now.sopt.sopt_kakaopay.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PayMoneyData(
    @SerialName("payMoney")
    val PayMoney: String
)
