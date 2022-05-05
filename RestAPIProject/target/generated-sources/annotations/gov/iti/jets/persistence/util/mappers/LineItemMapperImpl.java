package gov.iti.jets.persistence.util.mappers;

import gov.iti.jets.persistence.entities.LineItem;
import gov.iti.jets.presentation.dtos.LineItemDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-04T19:12:40+0200",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.13 (Eclipse Adoptium)"
)
@Component
public class LineItemMapperImpl implements LineItemMapper {

    @Override
    public LineItem lineItemDtoToEntity(LineItemDto lineItemDto) {
        if ( lineItemDto == null ) {
            return null;
        }

        LineItem lineItem = new LineItem();

        lineItem.setId( lineItemDto.getId() );
        lineItem.setProductName( lineItemDto.getProductName() );
        lineItem.setProductQuantity( lineItemDto.getProductQuantity() );
        if ( lineItemDto.getProductPrice() != null ) {
            lineItem.setProductPrice( lineItemDto.getProductPrice() );
        }

        return lineItem;
    }

    @Override
    public LineItemDto lineItemToDto(LineItem lineItem) {
        if ( lineItem == null ) {
            return null;
        }

        LineItemDto lineItemDto = new LineItemDto();

        lineItemDto.setId( lineItem.getId() );
        lineItemDto.setProductName( lineItem.getProductName() );
        lineItemDto.setProductQuantity( lineItem.getProductQuantity() );
        lineItemDto.setProductPrice( lineItem.getProductPrice() );

        return lineItemDto;
    }
}
