package gov.iti.jets.presentation.dtos;

import java.io.Serializable;

import gov.iti.jets.persistence.entities.CartId;
import jakarta.xml.bind.annotation.XmlRootElement;
import gov.iti.jets.persistence.util.ValidationUtil;

@XmlRootElement
public class CartProductsDto implements Serializable {
    private CartId cartId;

    private int totalPrice;

    private int quantity;

    public CartProductsDto() {
    }

    public CartProductsDto(CartId cartId, int totalPrice, int quantity) {
        this.cartId = cartId;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        ValidationUtil.getInstance().validate(this);

    }

    public CartId getCartId() {
        return cartId;
    }

    public void setCartId(CartId cartId) {
        this.cartId = cartId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartProductsDto [cartId=" + cartId + ", quantity=" + quantity + ", totalPrice=" + totalPrice + "]";
    }

}
