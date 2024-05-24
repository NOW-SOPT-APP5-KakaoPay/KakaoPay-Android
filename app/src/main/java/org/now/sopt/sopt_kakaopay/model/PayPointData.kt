package org.now.sopt.sopt_kakaopay.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PayPointData(
    @SerialName("payPoint")
    val PayPoint: String
)
