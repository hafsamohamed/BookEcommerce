package gov.iti.jets.services.impl;

import gov.iti.jets.persistence.ProductDao;
import gov.iti.jets.persistence.entities.Product;
import gov.iti.jets.persistence.impl.ProductDaoImpl;
import gov.iti.jets.presentation.dtos.ProductDto;
import gov.iti.jets.services.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Override
    public boolean addProduct(Product product) {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.addProduct(product);
    }


    @Override
    public Long getNoOfRecords() {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.getNoOfRecords();
    }

    @Override
    public Product getProductById(int id) {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.getProductById(id);
    }

    public boolean removeProduct(int id) {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.removeProduct(id);
    }

    @Override
    public boolean editProduct(ProductDto product, String name) {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.editProduct(product, name);
    }
      
    public List<Product> getAllProducts(){
        ProductDao productDao = new ProductDaoImpl();
        return productDao.getAllProducts();

    }


    @Override
    public Product getProductByName(String name) {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.getProductByName(name);
    }
}
