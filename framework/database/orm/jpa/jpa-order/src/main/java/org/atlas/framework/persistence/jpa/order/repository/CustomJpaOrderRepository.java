package org.atlas.framework.persistence.jpa.order.repository;

import org.atlas.business.order.domain.repository.FindOrderCondition;
import org.atlas.framework.persistence.jpa.order.entity.JpaOrder;

import java.util.List;

public interface CustomJpaOrderRepository {

    List<JpaOrder> find(FindOrderCondition condition);
    long count(FindOrderCondition condition);
}
