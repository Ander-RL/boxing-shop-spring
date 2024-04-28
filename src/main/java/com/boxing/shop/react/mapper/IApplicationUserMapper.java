package com.boxing.shop.react.mapper;
import com.boxing.shop.react.dto.PostRegistrationUserDto;
import com.boxing.shop.react.entity.ApplicationUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IApplicationUserMapper {

    @Mapping(target = "username", source = "dto.username")
    @Mapping(target = "password", source = "dto.password")
    @Mapping(target = "email", source = "dto.email")
    ApplicationUser dtoToEntity (PostRegistrationUserDto dto);

}
