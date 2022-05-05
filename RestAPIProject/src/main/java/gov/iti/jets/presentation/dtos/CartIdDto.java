package gov.iti.jets.presentation.dtos;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlRootElement;
import gov.iti.jets.persistence.util.ValidationUtil;

@XmlRootElement
public class CartIdDto implements Serializable {
    private int userId;
    private int productId;

    public CartIdDto() {
    }

    public CartIdDto(int userId, int productId) {
        this.userId = userId;
        this.productId = productId;
        ValidationUtil.getInstance().validate( this );

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "CartIdDto [productId=" + productId + ", userId=" + userId + "]";
    }

    
}
