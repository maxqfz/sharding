package net.maxqfz.sharding.model.converter

import net.maxqfz.sharding.model.dto.CustomerDto
import net.maxqfz.sharding.model.entity.CustomerEntity
import org.springframework.stereotype.Component

@Component
class CustomerConverter {
    fun convertDtoToEntity(dto: CustomerDto): CustomerEntity {
        return CustomerEntity(
                name = dto.name
        )
    }

    fun convertEntityToDto(entity: CustomerEntity): CustomerDto {
        return CustomerDto(
                id = entity.id,
                name = entity.name
        )
    }
}