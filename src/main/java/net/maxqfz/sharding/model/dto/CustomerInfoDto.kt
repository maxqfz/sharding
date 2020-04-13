package net.maxqfz.sharding.model.dto

import java.math.BigDecimal

data class CustomerInfoDto(
        val id: Long,
        val name: String,
        val totalSent: BigDecimal
)