package gov.iti.jets.persistence.util.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import gov.iti.jets.persistence.entities.User;
import gov.iti.jets.presentation.dtos.UserDto;

@Mapper(uses = { CartProductsMapper.class, OrderMapper.class })

public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDtoToEntity(UserDto userDto);

    UserDto userToDto(User user);

}
