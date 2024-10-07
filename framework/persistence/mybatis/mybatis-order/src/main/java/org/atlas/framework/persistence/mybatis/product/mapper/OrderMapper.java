package org.atlas.framework.persistence.mybatis.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.atlas.order.domain.entity.Order;
import org.atlas.order.domain.entity.OrderItem;
import org.atlas.order.domain.repository.FindOrderCondition;
import org.atlas.order.domain.shared.enums.OrderStatus;

import java.util.Date;
import java.util.List;

@Mapper
public interface OrderMapper {

    List<Order> find(@Param("condition") FindOrderCondition condition);
    long count(@Param("condition") FindOrderCondition condition);
    Order findById(@Param("id") Integer id);
    int insertOrder(Order order);
    int insertOrderItem(OrderItem orderItem);
    int update(Order order);
    int deleteById(@Param("id") Integer id);
    List<Order> findByStatusAndCreatedAtBefore(@Param("status")OrderStatus status, @Param("date") Date date);
}
