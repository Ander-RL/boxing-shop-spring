package com.boxing.shop.react.mapper;
import com.boxing.shop.react.dto.GetProductDto;
import com.boxing.shop.react.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IProductMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "img", source = "img")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "description", source = "description")
    GetProductDto entityToDto (Product entity);

}
