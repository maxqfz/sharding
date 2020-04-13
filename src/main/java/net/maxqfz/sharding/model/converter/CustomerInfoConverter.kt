package net.maxqfz.sharding.model.converter

import net.maxqfz.sharding.model.dto.CustomerInfoDto
import net.maxqfz.sharding.model.entity.CustomerEntity
import net.maxqfz.sharding.model.entity.PaymentEntity
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class CustomerInfoConverter {
    fun convertEntityToDto(entity: CustomerEntity): CustomerInfoDto {
        return CustomerInfoDto(
                id = entity.id,
                name = entity.name,
                totalSent = sumPayments(entity.outgoingPayments)
        )
    }

    private fun sumPayments(payments: List<PaymentEntity>): BigDecimal {
        var total = BigDecimal.ZERO
        for (payment in payments) {
            total = total.add(payment.sum)
        }
        return total
    }
}