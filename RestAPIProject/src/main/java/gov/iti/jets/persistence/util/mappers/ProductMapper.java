package gov.iti.jets.persistence.util.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import gov.iti.jets.persistence.entities.Product;
import gov.iti.jets.presentation.dtos.ProductDto;

@Mapper(uses = {CartProductsMapper.class})
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product productDtoToEntity(ProductDto productDto);

    ProductDto productToDto(Product product);
}
