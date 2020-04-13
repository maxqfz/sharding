package net.maxqfz.sharding.model.converter

import net.maxqfz.sharding.model.dto.PaymentDTO
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
    fun convertDtoToEntity(DTO: PaymentDTO): PaymentEntity {
        return PaymentEntity(
                payer = findCustomer(DTO.payerId),
                payee = findCustomer(DTO.payeeId),
                sum = DTO.sum
        )
    }

    fun convertEntityToDto(entity: PaymentEntity): PaymentDTO {
        return PaymentDTO(
                id = entity.id,
                payerId = entity.payer.id,
                payeeId = entity.payee.id,
                sum = entity.sum
        )
    }

    private fun findCustomer(id: Long): CustomerEntity {
        return customerRepository.findById(id)
                .orElseThrow { HttpClientErrorException(HttpStatus.BAD_REQUEST) }
        // for fail tolerance we can create new unknown user .orElse(CustomerEntity("Unknown"))
    }
}