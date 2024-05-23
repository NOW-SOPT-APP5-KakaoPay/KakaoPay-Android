package org.now.sopt.sopt_kakaopay.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionHistoryDto(
    @SerialName("bank")
    val bank: String,
    @SerialName("bankAccount")
    val bankAccount: String,
    @SerialName("name")
    val name: String,
    @SerialName("bookmark")
    val bookmark: Boolean
)
