package gov.iti.jets.persistence.impl;

import gov.iti.jets.persistence.ProductDao;
import gov.iti.jets.persistence.entities.Product;
import gov.iti.jets.persistence.util.ManagerFactory;
import gov.iti.jets.presentation.dtos.ProductDto;
import jakarta.persistence.*;

import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private final static EntityManagerFactory entityManagerFactory = ManagerFactory.getEntityManagerFactory();
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();
    private static Long noOfRecords;

    @Override
    public boolean addProduct(Product product) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(product);
        transaction.commit();
        return true;
    }

    @Override
    public Product getProductById(int id) {
        Product product = null;
        try {
            product = entityManager.find(Product.class, id);
        } catch (NoResultException nre) {
        }
        return product;
    }

    @Override
    public Long getNoOfRecords() {
        return noOfRecords;
    }

    @Override
    public boolean removeProduct(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String select = "SELECT p FROM Product p where p.id=:id";
        Query query = entityManager.createQuery(select);
        query.setParameter("id", id);
        List<Product> product = query.getResultList();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(product.get(0));
        transaction.commit();
        entityManager.close();
        return true;
    }

    @Override
    public boolean editProduct(ProductDto product, String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String select = "SELECT product FROM Product product WHERE product.name=:name";
        Query query = entityManager.createQuery(select);
        query.setParameter("name", product.getName());

        List<Product> products = query.getResultList();
        Product updatedProduct = products.get(0);

        updatedProduct.setName(product.getName());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setQuantity(product.getQuantity());

        entityManager.persist(updatedProduct);
        transaction.commit();
        entityManager.close();
        return true;
    }

    @Override
    public List<Product> getAllProducts() {
        TypedQuery<Product> query = entityManager.createQuery("select m from Product m", Product.class);
        List<Product> messages = query.getResultList();
        return messages;
    }

    @Override
    public Product getProductByName(String name) {
        Product product = null;
        try {
            Query query = entityManager
                    .createQuery("SELECT product FROM Product product WHERE product.name=:name");
            query.setParameter("name", name);
            product = (Product) query.getSingleResult();
        } catch (NoResultException nre) {
        }
        return product;
    }
}
