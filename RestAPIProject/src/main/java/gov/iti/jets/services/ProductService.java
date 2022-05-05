package gov.iti.jets.services;

import gov.iti.jets.persistence.entities.Product;
import gov.iti.jets.presentation.dtos.ProductDto;

import java.util.List;

public interface ProductService {
    public boolean addProduct(Product product);
    public Product getProductById(int id);
    public Product getProductByName(String name);

    public Long getNoOfRecords();

    public boolean removeProduct(int id);
    public boolean editProduct(ProductDto product, String name);
    public List<Product> getAllProducts();


}
