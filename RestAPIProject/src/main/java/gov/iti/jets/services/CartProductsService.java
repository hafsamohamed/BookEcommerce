package gov.iti.jets.services;

import gov.iti.jets.persistence.entities.CartProducts;
import gov.iti.jets.presentation.dtos.CartIdDto;

import java.util.List;

public interface CartProductsService {
    boolean addCartProduct(CartProducts cartProducts);
    boolean checkIfProductExist(Integer userId, Integer productId);
    boolean updateProduct(int productId, int userId, int quantity, double price);
    boolean addProductToCart(CartProducts cartProducts);
    List<CartProducts> getCartList (int id);
    List<CartProducts> getAllCarts();
    boolean removeCartProduct(CartIdDto cartId);

}
