package com.boxing.shop.react.mapper;
import com.boxing.shop.react.dto.GetOrderDto;
import com.boxing.shop.react.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IOrderMapper {

    @Mapping(target = "idOrder", source = "idOrder")
    @Mapping(target = "idCustomer", source = "idCustomer")
    @Mapping(target = "idProduct", source = "idProduct")
    @Mapping(target = "quantity", source = "quantity")
    GetOrderDto entityToDto (Order entity);

}
