package net.maxqfz.sharding.model.dto

import java.math.BigDecimal

data class PaymentDTO(
        /** Идентификатор платежа */
        val id: Long? = null,

        /** Идентификатор отправителя платежа */
        val payerId: Long = 0,

        /** Идентификатор получателя платежа */
        val payeeId: Long = 0,

        /** Сумма платежа */
        val sum: BigDecimal
)