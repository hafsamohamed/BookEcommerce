package gov.iti.jets.persistence.util.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import gov.iti.jets.persistence.entities.CartProducts;
import gov.iti.jets.presentation.dtos.CartProductsDto;

@Mapper(uses = { ProductMapper.class, UserMapper.class, CartIdMapper.class })
public interface CartProductsMapper {
    CartProductsMapper INSTANCE = Mappers.getMapper(CartProductsMapper.class);

    CartProducts cartProductsDtoToEntity(CartProductsDto cartProductsDto);

    CartProductsDto cartProductsToDto(CartProducts cartProducts);

    // List<CartProducts> dtoListToEntityList (List<CartProducts> cartProducts);
    // List<CartProductsDto> entityListToDtoList(List<CartProductsDto>
    // cartProductsDtos);

}
