package net.maxqfz.sharding.model.converter

import net.maxqfz.sharding.model.dto.CustomerDTO
import net.maxqfz.sharding.model.entity.CustomerEntity
import org.springframework.stereotype.Component

@Component
class CustomerConverter {
    fun convertDtoToEntity(DTO: CustomerDTO): CustomerEntity {
        return CustomerEntity(
                name = DTO.name
        )
    }

    fun convertEntityToDto(entity: CustomerEntity): CustomerDTO {
        return CustomerDTO(
                id = entity.id,
                name = entity.name
        )
    }
}