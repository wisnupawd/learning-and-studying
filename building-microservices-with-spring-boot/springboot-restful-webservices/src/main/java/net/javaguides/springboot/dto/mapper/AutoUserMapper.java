package net.javaguides.springboot.dto.mapper;

import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AutoUserMapper {

//    @Mapping(source = "email", target="emailAddress")
    UserDto mapToUserDto(User user);
    User mapToUser(UserDto userDto);
}
