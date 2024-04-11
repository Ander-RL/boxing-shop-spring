package com.boxing.shop.react.mapper;
import com.boxing.shop.react.dto.PostApplicationUserDto;
import com.boxing.shop.react.entity.ApplicationUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IApplicationUserMapper {

    @Mapping(target = "username", source = "dto.username")
    @Mapping(target = "password", source = "dto.password")
    ApplicationUser dtoToEntity (PostApplicationUserDto dto);

}
