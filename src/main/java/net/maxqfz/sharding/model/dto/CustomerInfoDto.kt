package net.maxqfz.sharding.model.dto

import java.math.BigDecimal

data class CustomerInfoDto(
        /** Идентификатор пользователя */
        val id: Long,

        /** Имя пользователя */
        val name: String,

        /** Сумма отправленных пользователем платежей */
        val totalSent: BigDecimal
)