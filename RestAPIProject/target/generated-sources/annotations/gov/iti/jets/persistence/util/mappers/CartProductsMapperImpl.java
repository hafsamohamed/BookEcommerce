package gov.iti.jets.persistence.util.mappers;

import gov.iti.jets.persistence.entities.CartProducts;
import gov.iti.jets.presentation.dtos.CartProductsDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-04T19:12:40+0200",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.13 (Eclipse Adoptium)"
)
@Component
public class CartProductsMapperImpl implements CartProductsMapper {

    @Override
    public CartProducts cartProductsDtoToEntity(CartProductsDto cartProductsDto) {
        if ( cartProductsDto == null ) {
            return null;
        }

        CartProducts cartProducts = new CartProducts();

        cartProducts.setCartId( cartProductsDto.getCartId() );
        cartProducts.setTotalPrice( cartProductsDto.getTotalPrice() );
        cartProducts.setQuantity( cartProductsDto.getQuantity() );

        return cartProducts;
    }

    @Override
    public CartProductsDto cartProductsToDto(CartProducts cartProducts) {
        if ( cartProducts == null ) {
            return null;
        }

        CartProductsDto cartProductsDto = new CartProductsDto();

        cartProductsDto.setCartId( cartProducts.getCartId() );
        cartProductsDto.setTotalPrice( cartProducts.getTotalPrice() );
        cartProductsDto.setQuantity( cartProducts.getQuantity() );

        return cartProductsDto;
    }
}
