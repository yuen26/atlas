package org.atlas.framework.persistence.jpa.order.repository;

import org.atlas.framework.persistence.jpa.core.repository.JpaBaseRepository;
import org.atlas.framework.persistence.jpa.order.entity.JpaOrder;
import org.atlas.order.domain.shared.enums.OrderStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface JpaOrderRepository extends JpaBaseRepository<JpaOrder, Integer> {

    @Query("select o from JpaOrder o left join fetch o.orderItems where o.id = :id")
    Optional<JpaOrder> findByIdAndFetch(@Param("id") Integer id);

    List<JpaOrder> findByStatusAndCreatedAtBefore(OrderStatus status, Date date);
}
