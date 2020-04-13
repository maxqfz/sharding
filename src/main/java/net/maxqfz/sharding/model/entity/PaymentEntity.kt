package net.maxqfz.sharding.model.entity

import org.hibernate.annotations.Cascade
import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "payment")
data class PaymentEntity(
        @JoinColumn(name = "payer_id", nullable = false)
        @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST])
        val payer: CustomerEntity,

        @JoinColumn(name = "payee_id", nullable = false)
        @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST])
        val payee: CustomerEntity,

        @Column(name = "sum", precision = 19, scale = 4, nullable = false)
        val sum: BigDecimal
) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @GenericGenerator(name = "customer_seq", strategy = "net.maxqfz.sharding.util.SnowflakeIdGenerator")
    val id: Long = 0
}