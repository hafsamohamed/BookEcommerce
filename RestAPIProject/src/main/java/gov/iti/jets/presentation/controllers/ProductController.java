package gov.iti.jets.presentation.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import gov.iti.jets.persistence.entities.Product;
import gov.iti.jets.persistence.util.mappers.ProductMapper;
import gov.iti.jets.presentation.dtos.ProductDto;
import gov.iti.jets.services.ProductService;
import gov.iti.jets.services.impl.ProductServiceImpl;
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

@Path("products")
public class ProductController {

    @Autowired
    public static ProductService productService = new ProductServiceImpl();
    @Autowired
    public static ProductMapper productMapper = ProductMapper.INSTANCE;

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getAllProducts() {
        List<ProductDto> products = new ArrayList<>();
        productService.getAllProducts().forEach(product -> products.add(productMapper.productToDto(product)));
        if (products.isEmpty()) {
            return Response.ok().entity("There is no products").build();
        }
        products.forEach(product -> System.out.println(product.getName()));
        return Response.ok().entity(products).build();
    }

    @GET
    @Path("{pid}")
    @Produces({ "application/xml", "application/json" })
    public Response getProducrByID(@PathParam("pid") int id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return Response.ok().entity("There is no product with this id add Product first").build();
        }
        ProductDto productDto = productMapper.productToDto(product);
        return Response.ok().entity(productDto).build();
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response addProduct(ProductDto productDto) {
        Product product = ProductMapper.INSTANCE.productDtoToEntity(productDto);
        ProductDto product2 = ProductMapper.INSTANCE.productToDto(productService.getProductByName(product.getName()));
        if (product2 == null) {
            productService.addProduct(product);
            return Response.ok().entity("Successfully Added").build();
        }
        return Response.ok().entity("there is Product hava this name update it first").build();

    }

    @PUT
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateUser(ProductDto productDto) {
        ProductDto product2 = ProductMapper.INSTANCE.productToDto(productService.getProductByName(productDto.getName()));

        if (product2 == null) {
            return Response.ok().entity("There is NO product hava this name").build();
        }
        productService.editProduct(productDto, product2.getName());
        return Response.ok().entity("Successfully Updated").build();

    }

    @DELETE
    @Path("{oid}")
    public Response deleteOrder(@PathParam("oid") int id) {
        ProductDto product = ProductMapper.INSTANCE.productToDto(productService.getProductById(id));
        if (product == null) {
            return Response.ok().entity("There is no product to delete it ").build();
        }
        productService.removeProduct(id);
        return Response.ok().entity("Successfully Deteted").build();
    }
}
