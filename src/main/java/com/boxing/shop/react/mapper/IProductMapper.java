package com.boxing.shop.react.mapper;
import com.boxing.shop.react.dto.GetOrderProductDto;
import com.boxing.shop.react.dto.GetProductDto;
import com.boxing.shop.react.dto.PostOrderProductDto;
import com.boxing.shop.react.entity.OrderProduct;
import com.boxing.shop.react.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IProductMapper {

    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "keyword", source = "keyword")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "img", source = "img")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "unitaryAmount", source = "unitaryAmount")
    @Mapping(target = "description", source = "description")
    GetProductDto entityToDto (Product entity);

    @Mapping(target = "purchasedProduct", source = "purchasedProduct")
    @Mapping(target = "quantity", source = "quantity")
    GetOrderProductDto entityToDto (OrderProduct entity);

    @Mapping(target = "purchasedProduct", source = "purchasedProduct")
    @Mapping(target = "quantity", source = "quantity")
    OrderProduct dtoToEntity (PostOrderProductDto dto);


    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "keyword", source = "keyword")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "img", source = "img")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "unitaryAmount", source = "unitaryAmount")
    @Mapping(target = "description", source = "description")
    Product getProductDtoToEntity (GetProductDto entity);

}
