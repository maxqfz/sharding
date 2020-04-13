package net.maxqfz.sharding.model.dto

import javax.validation.constraints.NotNull

data class CustomerDto(
        /** Идентификатор пользователя */
        val id: Long? = null,

        /** Имя пользователя */
        @field:NotNull
        val name: String
)