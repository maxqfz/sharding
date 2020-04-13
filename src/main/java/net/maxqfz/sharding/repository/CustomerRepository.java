package net.maxqfz.sharding.repository;

import net.maxqfz.sharding.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    @EntityGraph(attributePaths = {"outgoingPayments"})
    @Query("select c from CustomerEntity c where c.id = :id")
    Optional<CustomerEntity> findByIdWithOutgoingPayments(long id);
}
