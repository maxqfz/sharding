package net.maxqfz.sharding.converter

import io.mockk.every
import io.mockk.mockk
import net.maxqfz.sharding.model.converter.PaymentConverter
import net.maxqfz.sharding.model.dto.PaymentDTO
import net.maxqfz.sharding.model.entity.CustomerEntity
import net.maxqfz.sharding.model.entity.PaymentEntity
import net.maxqfz.sharding.repository.CustomerRepository
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.*

class PaymentConverterTest {
    private val paymentConverter = PaymentConverter(generateRepositoryMock())

    private fun generateRepositoryMock(): CustomerRepository {
        val repository: CustomerRepository = mockk(relaxed = true)
        every { repository.findById(any()) }
                .returns(Optional.of(CustomerEntity("Test")))
        return repository
    }

    @Test
    fun testDtoToEntityConversion() {
        //given
        val dto = PaymentDTO(
                payeeId = 0L,
                payerId = 0L,
                sum = BigDecimal.TEN
        )

        //when
        val entity = paymentConverter.convertDtoToEntity(dto)

        //then
        assertAll("Entity fields should be converted correctly",
                { assertEquals(0L, entity.id) },
                { assertEquals(0L, entity.payee.id) },
                { assertEquals(0L, entity.payer.id) },
                { assertEquals(BigDecimal.TEN, entity.sum) }
        )
    }

    @Test
    fun testEntityToDtoConversion() {
        //given
        val entity = PaymentEntity(
                payer = CustomerEntity("Payer"),
                payee = CustomerEntity("Payee"),
                sum = BigDecimal.TEN
        )

        //when
        val dto = paymentConverter.convertEntityToDto(entity)

        //then
        assertAll("Entity fields should be converted correctly",
                { assertEquals(0L, dto.id) },
                { assertEquals(0L, dto.payerId) },
                { assertEquals(0L, dto.payeeId) },
                { assertEquals(BigDecimal.TEN, dto.sum) }
        )
    }
}