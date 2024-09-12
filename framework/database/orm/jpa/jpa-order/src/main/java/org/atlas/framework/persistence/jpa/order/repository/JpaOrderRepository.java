package org.atlas.framework.persistence.jpa.order.repository;

import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.framework.persistence.jpa.core.repository.JpaBaseRepository;
import org.atlas.framework.persistence.jpa.order.entity.JpaOrder;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface JpaOrderRepository extends JpaBaseRepository<JpaOrder, Integer> {

    @Query("select o from JpaOrder o left join fetch o.orderItems where o.id = :id")
    Optional<JpaOrder> findByIdAndFetch(@Param("id") Integer id);

    @Modifying
    @Query("update JpaOrder o set o.deleted = true where o.status = :status and o.createdAt < :date")
    int softDeleteByStatusAndCreatedBefore(@Param("status") OrderStatus status,
                                           @Param("data") Date date);
}
