package net.maxqfz.sharding.model.dto

data class CustomerDto(
        /** Идентификатор пользователя */
        val id: Long? = null,

        /** Имя пользователя */
        val name: String
)