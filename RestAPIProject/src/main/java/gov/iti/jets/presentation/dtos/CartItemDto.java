package gov.iti.jets.presentation.dtos;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlRootElement;

import gov.iti.jets.persistence.entities.Product;
import gov.iti.jets.persistence.util.ValidationUtil;

@XmlRootElement
public class CartItemDto implements Serializable{
    private Product product;
    private int quantity;

    public CartItemDto() {
    }

    public CartItemDto(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        ValidationUtil.getInstance().validate( this );

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItemDto [product=" + product + ", quantity=" + quantity + "]";
    }
    
}
