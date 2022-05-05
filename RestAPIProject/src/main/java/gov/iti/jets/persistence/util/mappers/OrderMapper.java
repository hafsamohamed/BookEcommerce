package gov.iti.jets.persistence.util.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import gov.iti.jets.persistence.entities.Order;
import gov.iti.jets.presentation.dtos.OrderDto;

@Mapper(uses = { LineItemMapper.class, UserMapper.class })
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order orderDtoToEntity(OrderDto productDto);

    OrderDto orderToDto(Order order);

    List<Order> dtoListToEntityList(List<Order> singleMessageDtoList);

    List<OrderDto> entityListToDtoList(List<OrderDto> singleMessageEntityList);

}
