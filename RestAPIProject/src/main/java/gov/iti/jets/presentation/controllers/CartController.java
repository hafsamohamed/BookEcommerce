package gov.iti.jets.presentation.controllers;

import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.persistence.entities.CartId;
import gov.iti.jets.persistence.entities.CartProducts;
import gov.iti.jets.persistence.entities.Product;
import gov.iti.jets.persistence.entities.User;
import gov.iti.jets.persistence.util.mappers.CartProductsMapper;
import gov.iti.jets.presentation.dtos.CartIdDto;
import gov.iti.jets.presentation.dtos.CartProductsDto;
import gov.iti.jets.services.CartProductsService;
import gov.iti.jets.services.ProductService;
import gov.iti.jets.services.UserService;
import gov.iti.jets.services.impl.CartProductsServiceImpl;
import gov.iti.jets.services.impl.ProductServiceImpl;
import gov.iti.jets.services.impl.UserServiceImpl;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("cart")
public class CartController {
    public static CartProductsMapper cartProductsMapper = CartProductsMapper.INSTANCE;
    public static CartProductsService cartProductsService = new CartProductsServiceImpl();
    public static UserService userService = new UserServiceImpl();
    public static ProductService productService = new ProductServiceImpl();
    boolean check = false;

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getAllCarts() {
        List<CartProductsDto> cartProductsDtos = new ArrayList<>();
        cartProductsService.getAllCarts()
                .forEach(cart -> cartProductsDtos.add(cartProductsMapper.cartProductsToDto(cart)));

        if (cartProductsDtos.isEmpty()) {
            return Response.ok().entity("There is no products on the carts").build();
        }
        return Response.ok().entity(cartProductsDtos).build();
    }

    @GET
    @Path("{pid}")
    @Produces({ "application/xml", "application/json" })
    public Response getAllCartProducts(@PathParam("pid") int id) {
        User user = userService.getUser(id);

        if (user == null) {
            return Response.ok().entity("You need to register first").build();
        }

        List<CartProductsDto> cartProductsDtos = new ArrayList<>();
        cartProductsService.getCartList(id)
                .forEach(cart -> cartProductsDtos.add(cartProductsMapper.cartProductsToDto(cart)));

        if (cartProductsDtos.isEmpty()) {
            return Response.ok().entity("There is no products on the carts").build();
        }
        return Response.ok().entity(cartProductsDtos).build();
    }

    @POST
    @Path("{cid}/{pid}/{quantity}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response addProductToCart(@PathParam("cid") int userId, @PathParam("pid") int productId,
            @PathParam("quantity") int quantity) {

        User user = userService.getUser(userId);
        if (user == null) {
            return Response.ok().entity("You need to register first").build();
        }

        Product product = productService.getProductById(productId);
        if (product == null) {
            return Response.ok().entity("There is no product with this id add Product first").build();
        }
        cartProductsService.getCartList(userId)
                .forEach(cart -> {
                    if (cart.getCartId().getProductId() == productId) {
                        check = true;
                    }
                });

        if (check) {
            check = false;
            return Response.ok().entity("Your Cart contains this product update it first").build();
        }
        CartProducts cartProducts = new CartProducts();
        cartProducts.setCartId(new CartId(userId, productId));
        cartProducts.setProduct(product);
        cartProducts.setUser(user);
        cartProducts.setQuantity(quantity);
        cartProducts.setTotalPrice((int) product.getPrice() * quantity);

        cartProductsService.addCartProduct(cartProducts);
        return Response.ok().entity("Sucessfully Added To Cart :)").build();

    }

    @PUT
    @Path("{cid}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateCartProduct(@PathParam("cid") int userId, Product product) {
        User user = userService.getUser(userId);
        if (user == null) {
            return Response.ok().entity("You need to register first").build();
        }

        Product product2 = productService.getProductByName(product.getName());
        if (product2 == null) {
            return Response.ok().entity("There is no product with this Name add Product first").build();
        }

        cartProductsService.getCartList(userId)
                .forEach(cart -> {
                    if (cart.getCartId().getProductId() == product2.getId()) {
                        check = true;
                    }
                });

        if (!check) {
            return Response.ok().entity("Your Cart does not contain this product add it first").build();
        }
        check = false;
        cartProductsService.updateProduct( product2.getId(), userId, product.getQuantity(), product.getPrice());
        return Response.ok().entity("Product Sucessfully Updated To Cart :)").build();
    }

    @DELETE
    @Path("{cid}/{pid}")
    public Response deleteOrder(@PathParam("cid") int userId, @PathParam("pid") int productId) {
        User user = userService.getUser(userId);
        if (user == null) {
            return Response.ok().entity("You need to register first").build();
        }

        Product product = productService.getProductById(productId);
        if (product == null) {
            return Response.ok().entity("There is no product with this id add Product first").build();
        }
        cartProductsService.getCartList(userId)
                .forEach(cart -> {
                    if (cart.getCartId().getProductId() == productId) {
                        check = true;
                    }
                });

                if (!check) {
                    return Response.ok().entity("Your Cart does not contain this product add it first").build();
                }
                check = false;
                CartIdDto cartIdDto = new CartIdDto(userId, productId);
                cartProductsService.removeCartProduct(cartIdDto);
                return Response.ok().entity("Product Sucessfully Deleted From Cart :)").build();
            }

}
