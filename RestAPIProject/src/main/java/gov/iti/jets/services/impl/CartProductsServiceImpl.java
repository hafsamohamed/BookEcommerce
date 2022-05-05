package gov.iti.jets.services.impl;

import gov.iti.jets.persistence.CartProductsDao;
import gov.iti.jets.persistence.entities.CartProducts;
import gov.iti.jets.persistence.impl.CartProductsDaoImpl;
import gov.iti.jets.presentation.dtos.CartIdDto;
import gov.iti.jets.services.CartProductsService;

import java.util.List;

public class CartProductsServiceImpl implements CartProductsService {
    @Override
    public boolean addCartProduct(CartProducts cartProducts) {
        CartProductsDao cartProductsDao = new CartProductsDaoImpl();
        return cartProductsDao.addCartProduct(cartProducts);
    }

    @Override
    public boolean checkIfProductExist(Integer userId, Integer productId) {
        CartProductsDao cartProductsDao = new CartProductsDaoImpl();
        return cartProductsDao.chechIfProductExist(userId, productId);
    }

    @Override
    public boolean updateProduct(int productId, int userId, int quantity, double price) {
        CartProductsDao cartProductsDao = new CartProductsDaoImpl();
        return cartProductsDao.updateProduct(productId, userId, quantity, price);
    }

    @Override
    public boolean addProductToCart(CartProducts cartProducts) {
        CartProductsDao cartProductsDao = new CartProductsDaoImpl();
        return cartProductsDao.addProductToCart(cartProducts);
    }

    @Override
    public List<CartProducts> getCartList(int id) {
        CartProductsDao cartProductsDao = new CartProductsDaoImpl();
        return cartProductsDao.getCartProductsList(id);
    }

    @Override
    public List<CartProducts> getAllCarts() {
        CartProductsDao cartProductsDao = new CartProductsDaoImpl();
        return cartProductsDao.getAllCarts();
    }

    @Override
    public boolean removeCartProduct(CartIdDto cartId) {
        CartProductsDao cartProductsDao = new CartProductsDaoImpl();
        return cartProductsDao.removeCartProduct(cartId);
    }
}
