package com.boxing.shop.react.mapper;
import com.boxing.shop.react.dto.GetAddressDto;
import com.boxing.shop.react.entity.UserAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IUserAddressMapper {

    @Mapping(target = "door", source = "entity.door")
    @Mapping(target = "floor", source = "entity.floor")
    @Mapping(target = "street", source = "entity.street")
    @Mapping(target = "city", source = "entity.city")
    @Mapping(target = "country", source = "entity.country")
    @Mapping(target = "postalCode", source = "entity.postalCode")
    GetAddressDto entityToDto (UserAddress entity);

}
