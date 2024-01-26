package com.boxing.shop.react.mapper;
import com.boxing.shop.react.dto.GetOrderDto;
import com.boxing.shop.react.dto.PostOrderDto;
import com.boxing.shop.react.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IOrderMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "idCustomer", source = "idCustomer")
    @Mapping(target = "idProduct", source = "idProduct")
    @Mapping(target = "idOrder", source = "idOrder")
    @Mapping(target = "quantity", source = "quantity")
    GetOrderDto entityToDto (Order entity);

    @Mapping(target = "idCustomer", source = "dto.idCustomer")
    @Mapping(target = "idProduct", source = "dto.idProduct")
    @Mapping(target = "idOrder", source = "idOrder")
    @Mapping(target = "quantity", source = "dto.quantity")
    Order dtoToEntity (PostOrderDto dto, Long idOrder);

}
