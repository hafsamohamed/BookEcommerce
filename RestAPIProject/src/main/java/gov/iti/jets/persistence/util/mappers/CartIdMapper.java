package gov.iti.jets.persistence.util.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import gov.iti.jets.persistence.entities.CartId;
import gov.iti.jets.presentation.dtos.CartIdDto;

@Mapper
public interface CartIdMapper {
    CartIdMapper INSTANCE = Mappers.getMapper(CartIdMapper.class);

    CartId cartIdDtoToEntity(CartIdDto cartIdDto);

    CartIdDto cartIdToDto(CartId cartId);

}
