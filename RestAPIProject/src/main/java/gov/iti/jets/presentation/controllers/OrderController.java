package gov.iti.jets.presentation.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.persistence.entities.Order;
import gov.iti.jets.persistence.entities.User;
import gov.iti.jets.persistence.util.mappers.CartIdMapper;
import gov.iti.jets.persistence.util.mappers.OrderMapper;
import gov.iti.jets.presentation.dtos.CartProductsDto;
import gov.iti.jets.presentation.dtos.LineItemDto;
import gov.iti.jets.presentation.dtos.OrderDto;
import gov.iti.jets.services.CartProductsService;
import gov.iti.jets.services.OrderService;
import gov.iti.jets.services.ProductService;
import gov.iti.jets.services.UserService;
import gov.iti.jets.services.impl.CartProductsServiceImpl;
import gov.iti.jets.services.impl.OrderServiceImpl;
import gov.iti.jets.services.impl.ProductServiceImpl;
import gov.iti.jets.services.impl.UserServiceImpl;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("order")
public class OrderController {
    public static OrderMapper orderMapper = OrderMapper.INSTANCE;
    public static CartProductsService cartProductsService = new CartProductsServiceImpl();
    public static UserService userService = new UserServiceImpl();
    public static ProductService productService = new ProductServiceImpl();
    public static OrderService orderService = new OrderServiceImpl();
    boolean check = false;
    int total_price = 0;

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getAllOrders() {
        List<OrderDto> orderDtos = new ArrayList<>();
        orderService.getAllOrders()
                .forEach(cart -> orderDtos.add(orderMapper.orderToDto(cart)));

        if (orderDtos.isEmpty()) {
            return Response.ok().entity("There is no Orders").build();
        }
        return Response.ok().entity(orderDtos).build();
    }

    @GET
    @Path("{pid}")
    @Produces({ "application/xml", "application/json" })
    public Response getAllCartProducts(@PathParam("pid") int id) {
        User user = userService.getUser(id);

        if (user == null) {
            return Response.ok().entity("You need to register first").build();
        }

        List<OrderDto> orderDtos = new ArrayList<>();
        orderService.getOrderByUserId(id)
                .forEach(cart -> orderDtos.add(orderMapper.orderToDto(cart)));

        if (orderDtos.isEmpty()) {
            return Response.ok().entity("There is no order for this user").build();
        }
        return Response.ok().entity(orderDtos).build();
    }

    @POST
    @Path("{cid}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response addProductToCart(@PathParam("cid") int userId) {

        User user = userService.getUser(userId);
        if (user == null) {
            return Response.ok().entity("You need to register first").build();
        }

        cartProductsService.getCartList(userId)
                .forEach(cart -> {
                    total_price += cart.getTotalPrice();
                });

        if (total_price <= 0) {
            return Response.ok().entity("There is no products on the carts").build();
        }
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(total_price);
        order.setOrderTime(LocalDate.now());
        orderService.saveOrder(order);
        cartProductsService.getCartList(userId)
                .forEach(cart -> cartProductsService
                        .removeCartProduct(CartIdMapper.INSTANCE.cartIdToDto(cart.getCartId())));

        return Response.ok().entity("Order is Saved Successfully").build();

    }

    @DELETE
    @Path("{cid}/{pid}")
    public Response deleteOrder(@PathParam("cid") int userId, @PathParam("pid") int orderId) {
        User user = userService.getUser(userId);
        if (user == null) {
            return Response.ok().entity("You need to register first").build();
        }

        Order order = orderService.getOrderById(userId, orderId);
        if (order == null) {
            return Response.ok().entity("There is no order with this id add Order first").build();
        }

        orderService.deleteOrder(userId, orderId);
        return Response.ok().entity("Order Sucessfully Deleted :)").build();
    }
}
