package org.now.sopt.sopt_kakaopay.service

import org.now.sopt.sopt_kakaopay.model.ResponseDto

interface ApiService {
    suspend fun getData(id: Int): ResponseDto
}
