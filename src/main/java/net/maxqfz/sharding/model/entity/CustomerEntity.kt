package net.maxqfz.sharding.model.entity

import org.hibernate.annotations.GenericGenerator
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "customer")
class CustomerEntity(
        @Column(name = "name", nullable = false)
        var name: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @GenericGenerator(name = "customer_seq", strategy = "net.maxqfz.sharding.util.SnowflakeIdGenerator")
    val id: Long = 0

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "payer")
    val outgoingPayments: List<PaymentEntity> = emptyList()

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "payee")
    val incomingPayments: List<PaymentEntity> = emptyList()
}