package net.maxqfz.sharding.model.converter

import net.maxqfz.sharding.model.dto.PaymentDto
import net.maxqfz.sharding.model.entity.CustomerEntity
import net.maxqfz.sharding.model.entity.PaymentEntity
import net.maxqfz.sharding.repository.CustomerRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException

@Component
class PaymentConverter(
        private val customerRepository: CustomerRepository
) {
    fun convertDtoToEntity(dto: PaymentDto): PaymentEntity {
        return PaymentEntity(
                payer = findCustomer(dto.payerId),
                payee = findCustomer(dto.payeeId),
                sum = dto.sum
        )
    }

    fun convertEntityToDto(entity: PaymentEntity): PaymentDto {
        return PaymentDto(
                id = entity.id,
                payerId = entity.payer.id,
                payeeId = entity.payee.id,
                sum = entity.sum
        )
    }

    private fun findCustomer(id: Long): CustomerEntity {
        return customerRepository.findById(id)
                .orElseThrow { HttpClientErrorException(HttpStatus.BAD_REQUEST) }
        // for fail tolerance we can create user .orElse(CustomerEntity("Unknown"))
    }
}