package gov.iti.jets.presentation.dtos;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlRootElement;
import gov.iti.jets.persistence.util.ValidationUtil;


@XmlRootElement
public class LineItemDto implements Serializable {
    private int id;
    private String productName;
    private int productQuantity;
    private Double productPrice;

    public LineItemDto() {

    }

    public LineItemDto(int id, String productName, int productQuantity, Double productPrice) {
        this.id = id;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "LineItemDto [id=" + id + ", productName=" + productName + ", productPrice=" + productPrice
                + ", productQuantity=" + productQuantity + "]";
    }
    

    
}
