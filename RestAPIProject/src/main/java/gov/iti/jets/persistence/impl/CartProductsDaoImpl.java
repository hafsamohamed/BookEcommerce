package gov.iti.jets.persistence.impl;

import gov.iti.jets.persistence.CartProductsDao;
import gov.iti.jets.persistence.entities.CartProducts;
import gov.iti.jets.persistence.entities.User;
import gov.iti.jets.persistence.util.ManagerFactory;
import gov.iti.jets.presentation.dtos.CartIdDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;

import java.util.List;

public class CartProductsDaoImpl implements CartProductsDao {
    private final static EntityManagerFactory entityManagerFactory = ManagerFactory.getEntityManagerFactory();
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public boolean addCartProduct(CartProducts cartProducts) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(cartProducts);
        transaction.commit();
        return true;
    }

    @Override
    public boolean chechIfProductExist(Integer userId, Integer productId) {
        CriteriaBuilder cb = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<CartProducts> query = cb.createQuery(CartProducts.class);
        Root<CartProducts> cartProducts = query.from(CartProducts.class);
        Predicate predicate = cb.and(cb.equal(cartProducts.get("user").<Integer>get("id"), userId),
                cb.equal(cartProducts.get("product").<String>get("id"), productId));
        query.select(cartProducts).where(predicate);
        List<CartProducts> result = entityManager.createQuery(query).getResultList();
        return result.size() > 0;
    }

    @Transactional
    @Override
    public boolean updateProduct(int productId, int userId, int quantity, double price) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        String select = "SELECT p FROM CartProducts p where p.cartId.userId=:userId and p.cartId.productId=:productId";

        Query query = entityManager.createQuery(select);
        query.setParameter("userId", userId);
        query.setParameter("productId", productId);

        CartProducts updateduser = (CartProducts) query.getSingleResult();

        updateduser.setQuantity(quantity);
        updateduser.setTotalPrice((int) price);

        entityManager.persist(updateduser);
        transaction.commit();
        entityManager.close();
        return true;

    }

    @Override
    public boolean addProductToCart(CartProducts cartProducts) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(cartProducts);
        transaction.commit();
        return true;
    }

    @Override
    public List<CartProducts> getCartProductsList(int id) {
        User user = entityManager.find(User.class, id);
        List<CartProducts> cartProductsList = user.getCartProductsList();
        return cartProductsList;
    }

    @Override
    public List<CartProducts> getAllCarts() {
        TypedQuery<CartProducts> query = entityManager.createQuery("select m from CartProducts m", CartProducts.class);
        List<CartProducts> messages = query.getResultList();
        return messages;
    }

    @Override
    public boolean removeCartProduct(CartIdDto cartId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String select = "SELECT p FROM CartProducts p where p.cartId.userId=:userId and p.cartId.productId=:productId";
        Query query = entityManager.createQuery(select);
        query.setParameter("userId", cartId.getUserId());
        query.setParameter("productId", cartId.getProductId());
        List<CartProducts> cartProducts = query.getResultList();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(cartProducts.get(0));
        transaction.commit();
        entityManager.close();
        return true;
    }
}
