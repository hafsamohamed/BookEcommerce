package gov.iti.jets.persistence;

import gov.iti.jets.persistence.entities.CartProducts;
import gov.iti.jets.presentation.dtos.CartIdDto;

import java.util.List;

public interface CartProductsDao {
    boolean addCartProduct(CartProducts cartProducts);
    boolean chechIfProductExist(Integer userId, Integer productId);
    boolean updateProduct(int productId, int userId, int quantity, double price);
    boolean addProductToCart(CartProducts cartProducts);
    List<CartProducts> getCartProductsList(int id);
    List<CartProducts> getAllCarts();
    boolean removeCartProduct(CartIdDto cartId);
}
