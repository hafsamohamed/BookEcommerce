package gov.iti.jets.persistence;

import gov.iti.jets.persistence.entities.Product;
import gov.iti.jets.presentation.dtos.ProductDto;

import java.util.List;

public interface ProductDao {
    boolean addProduct (Product product);
    Product getProductById(int id);
    //List <Product> loadProducts(int offset, int noOfRecords);
    Long getNoOfRecords();
    boolean removeProduct(int id);
    boolean editProduct(ProductDto product, String name);
    public List<Product> getAllProducts();
    Product getProductByName(String name);

}
