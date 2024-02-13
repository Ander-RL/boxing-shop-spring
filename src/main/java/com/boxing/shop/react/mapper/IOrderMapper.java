package com.boxing.shop.react.mapper;
import com.boxing.shop.react.dto.GetOrderDto;
import com.boxing.shop.react.dto.PostOrderDto;
import com.boxing.shop.react.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IOrderMapper {

    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "customerId", source = "customerId")
    @Mapping(target = "products", source = "products")
    @Mapping(target = "totalAmount", source = "totalAmount")
    GetOrderDto entityToDto (Order entity);

    @Mapping(target = "customerId", source = "dto.customerId")
    @Mapping(target = "products", source = "dto.products")
    @Mapping(target = "totalAmount", source = "dto.totalAmount")
    Order dtoToEntity (PostOrderDto dto);

}
