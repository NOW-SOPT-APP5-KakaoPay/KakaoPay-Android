package org.now.sopt.sopt_kakaopay.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookMarkResponseDto(
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: Int
)
