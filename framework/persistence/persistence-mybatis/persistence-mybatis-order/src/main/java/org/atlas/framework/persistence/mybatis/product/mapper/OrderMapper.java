package org.atlas.framework.persistence.mybatis.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.entity.OrderItem;
import org.atlas.business.order.domain.shared.enums.OrderStatus;

import java.util.Date;
import java.util.List;

@Mapper
public interface OrderMapper {

    List<Order> findAll(@Param("offset") int offset, @Param("limit") int limit);
    long countAll();
    Order findById(@Param("id") Integer id);
    int insertOrder(Order order);
    int insertOrderItem(OrderItem orderItem);
    int update(Order order);
    List<Order> findByStatusAndCreatedAtBefore(@Param("status")OrderStatus status, @Param("date") Date date);
}
