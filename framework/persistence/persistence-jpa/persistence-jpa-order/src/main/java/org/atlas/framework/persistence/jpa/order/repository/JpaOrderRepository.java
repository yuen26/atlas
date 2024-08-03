package org.atlas.framework.persistence.jpa.order.repository;

import org.atlas.framework.persistence.jpa.core.repository.JpaBaseRepository;
import org.atlas.framework.persistence.jpa.order.entity.JpaOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaOrderRepository extends JpaBaseRepository<JpaOrder, Integer> {

    @Query("select o from JpaOrder o left join fetch o.orderItems")
    Page<JpaOrder> findAllAndFetch(Pageable pageable);

    @Query("select o from JpaOrder o left join fetch o.orderItems where o.id = :id")
    Optional<JpaOrder> findByIdAndFetch(@Param("id") Integer id);
}
