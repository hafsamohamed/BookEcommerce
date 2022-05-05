package gov.iti.jets.persistence.util.mappers;

import gov.iti.jets.persistence.entities.Order;
import gov.iti.jets.presentation.dtos.OrderDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-04T19:12:40+0200",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.13 (Eclipse Adoptium)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order orderDtoToEntity(OrderDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Order order = new Order();

        order.setId( productDto.getId() );
        order.setTotalPrice( productDto.getTotalPrice() );
        order.setOrderTime( productDto.getOrderTime() );

        return order;
    }

    @Override
    public OrderDto orderToDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setId( order.getId() );
        orderDto.setTotalPrice( order.getTotalPrice() );
        orderDto.setOrderTime( order.getOrderTime() );

        return orderDto;
    }

    @Override
    public List<Order> dtoListToEntityList(List<Order> singleMessageDtoList) {
        if ( singleMessageDtoList == null ) {
            return null;
        }

        List<Order> list = new ArrayList<Order>( singleMessageDtoList.size() );
        for ( Order order : singleMessageDtoList ) {
            list.add( order );
        }

        return list;
    }

    @Override
    public List<OrderDto> entityListToDtoList(List<OrderDto> singleMessageEntityList) {
        if ( singleMessageEntityList == null ) {
            return null;
        }

        List<OrderDto> list = new ArrayList<OrderDto>( singleMessageEntityList.size() );
        for ( OrderDto orderDto : singleMessageEntityList ) {
            list.add( orderDto );
        }

        return list;
    }
}
