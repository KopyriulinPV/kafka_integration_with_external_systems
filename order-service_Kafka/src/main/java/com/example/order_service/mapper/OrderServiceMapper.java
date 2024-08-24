package com.example.order_service.mapper;
import com.example.order_service.DTO.OrderEvent;
import com.example.order_service.Entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderServiceMapper {

    OrderEvent orderToOrderEvent(Order order);
}
