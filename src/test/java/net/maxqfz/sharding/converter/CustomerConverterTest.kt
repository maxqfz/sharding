package net.maxqfz.sharding.converter

import net.maxqfz.sharding.model.converter.CustomerConverter
import net.maxqfz.sharding.model.dto.CustomerDTO
import net.maxqfz.sharding.model.entity.CustomerEntity
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CustomerConverterTest {
    private val customerConverter = CustomerConverter()

    @Test
    fun testDtoToEntityConversion() {
        //given
        val dto = CustomerDTO(
                name = "Test"
        )

        //when
        val entity = customerConverter.convertDtoToEntity(dto)

        //then
        assertAll("DTO fields should be converted correctly",
                { assertEquals(0, entity.id) },
                { assertEquals("Test", entity.name) }
        )
    }

    @Test
    fun testEntityToDtoConversion() {
        //given
        val entity = CustomerEntity(
                name = "Test"
        )

        //when
        val dto = customerConverter.convertEntityToDto(entity)

        //then
        assertAll("Entity fields should be converted correctly",
                { assertEquals(0, dto.id) },
                { assertEquals("Test", dto.name) }
        )
    }
}