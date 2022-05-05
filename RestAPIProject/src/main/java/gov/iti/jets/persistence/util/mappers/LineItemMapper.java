package gov.iti.jets.persistence.util.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import gov.iti.jets.persistence.entities.LineItem;
import gov.iti.jets.presentation.dtos.LineItemDto;

@Mapper(uses = {OrderMapper.class})
public interface LineItemMapper {
    LineItemMapper INSTANCE = Mappers.getMapper(LineItemMapper.class);

    LineItem lineItemDtoToEntity(LineItemDto lineItemDto);

    LineItemDto lineItemToDto(LineItem lineItem);
}
